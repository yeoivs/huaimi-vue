package com.ieng.huaimi.core.service.impl;

import com.ieng.huaimi.common.constant.HeaderConstant;
import com.ieng.huaimi.common.constant.KeyConstant;
import com.ieng.huaimi.common.constant.SessionConstant;
import com.ieng.huaimi.common.utils.IDUtil;
import com.ieng.huaimi.core.cache.RedisCache;
import com.ieng.huaimi.core.bean.UserAccredit;
import com.ieng.huaimi.core.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "jwt")
@Validated
@Service
public class TokenServiceImpl implements TokenService {
    @NotEmpty
    private String secret;
    @NotNull
    private Duration expiration;
    @Autowired
    private RedisCache redisCache;

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setExpiration(Duration expiration) {
        this.expiration = expiration;
    }


    @Override
    public String createToken(UserAccredit userAccredit) {
        String uuid = IDUtil.uuid();

        userAccredit.setToken(uuid);

        refreshToken(userAccredit);

        Map<String, Object> map = new HashMap<>(1);
        map.put(KeyConstant.USER_KEY, uuid);

        return createJWT(map);
    }

    @Override
    public UserAccredit getPrincipal(HttpServletRequest request) {
        String token = getToken(request);
        if(StringUtils.isNotEmpty(token)){
            Claims claims = parserJWT(token);
            String uuid = (String) claims.get(KeyConstant.USER_KEY);
            return redisCache.getObject(SessionConstant.USER_KEY_PREFIX + uuid);
        }
        return null;
    }

    @Override
    public void delToken(String uuid) {
        if(StringUtils.isNotEmpty(uuid)){
            String key = SessionConstant.USER_KEY_PREFIX + uuid;
            if(redisCache.hasKey(key)){
                redisCache.delObject(key);
            }
        }
    }

    @Override
    public void refreshToken(UserAccredit userAccredit) {
        userAccredit.setAccessTime(System.currentTimeMillis());
        userAccredit.setExpireTime(userAccredit.getAccessTime() + this.expiration.toMillis() * 1000);
        redisCache.setObject(SessionConstant.USER_KEY_PREFIX + userAccredit.getToken(), userAccredit, this.expiration.toMinutes() * 60);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(HeaderConstant.HEADER);
        if(StringUtils.isNotEmpty(token) && token.startsWith(HeaderConstant.TOKEN_PREFIX)){
            return token.replace(HeaderConstant.TOKEN_PREFIX, "");
        }
        return null;
    }

    private String createJWT(Map<String, Object> claims){
        //Date time = new Date(System.currentTimeMillis() + expiration * 1000);
        return Jwts.builder()
                .setClaims(claims)
                //.setExpiration(time)
                .signWith(SignatureAlgorithm.HS256, secretKeySpec(this.secret))
                .compact();
    }

    private Claims parserJWT(String token){
        return Jwts.parser()
                .setSigningKey(secretKeySpec(this.secret))
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKeySpec secretKeySpec(String secret){
        byte[] encodeKey = Base64.decodeBase64(secret);
        return new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
    }

    /*private Boolean isTokenExpired(String token){
        try{
            Claims claims = parserJWT(token);
            Date time = claims.getExpiration();
            return time.before(new Date());
        } catch (Exception e){
            return false;
        }
    }*/
}
