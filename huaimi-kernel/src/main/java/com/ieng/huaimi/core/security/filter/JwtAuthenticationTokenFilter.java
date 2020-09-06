package com.ieng.huaimi.core.security.filter;

import com.ieng.huaimi.core.bean.UserAccredit;
import com.ieng.huaimi.core.service.TokenService;
import com.ieng.huaimi.core.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private static final long MINUTES_10 = 10 * 60 * 1000L;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        UserAccredit userAccredit = tokenService.getPrincipal(request);

        if(userAccredit != null){
            refreshExpireTime(userAccredit);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userAccredit, null, userAccredit.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 刷新缓存Token过期时间
     * @param userAccredit 用户
     */
    private void refreshExpireTime(UserAccredit userAccredit){
        long expireTime = userAccredit.getExpireTime();
        long currentTime = System.currentTimeMillis();
        String format = String.format("Token ==> %d - %d = %d <= %d", expireTime, currentTime, (expireTime - currentTime), MINUTES_10);
        System.out.println(format);
        if(expireTime - currentTime <= MINUTES_10){
            String uuid = userAccredit.getToken();
            userAccredit = (UserAccredit) userDetailsService.loadUserByUsername(userAccredit.getUsername());
            userAccredit.setToken(uuid);
            tokenService.refreshToken(userAccredit);
        }
    }

}
