package com.ieng.huaimi.core.security.filter;

import com.ieng.huaimi.core.bean.UserPrincipal;
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
        UserPrincipal userPrincipal = tokenService.getPrincipal(request);

        if(userPrincipal != null){
            refreshExpireTime(userPrincipal);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 刷新缓存Token过期时间
     * @param userPrincipal 用户
     */
    private void refreshExpireTime(UserPrincipal userPrincipal){
        long expireTime = userPrincipal.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if(expireTime - currentTime <= MINUTES_10){
            String uuid = userPrincipal.getToken();
            userPrincipal = (UserPrincipal) userDetailsService.loadUserByUsername(userPrincipal.getUsername());
            userPrincipal.setToken(uuid);
            tokenService.refreshToken(userPrincipal);
        }
    }

}
