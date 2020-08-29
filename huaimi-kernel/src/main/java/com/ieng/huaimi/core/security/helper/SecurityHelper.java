package com.ieng.huaimi.core.security.helper;

import com.ieng.huaimi.common.domain.ResultBody;
import com.ieng.huaimi.common.enums.ServiceStatus;
import com.ieng.huaimi.common.utils.context.ServletContextHolder;
import com.ieng.huaimi.common.utils.StringUtil;
import com.ieng.huaimi.core.bean.UserPrincipal;
import com.ieng.huaimi.core.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * <p>
 *     Security 辅助类
 * </p>
 */
@Configuration
public class SecurityHelper {
    public static final Logger LOGGER = LoggerFactory.getLogger(SecurityHelper.class);
    @Autowired
    private TokenService tokenService;

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> {
            LOGGER.error(e.getMessage(), e);
            ServletContextHolder.sendJSON(response,
                    ResultBody.effect(ServiceStatus.UNAUTHORIZED, "认证失败，无法访问该资源", null));
            //response.sendError(ServiceStatus.UNAUTHORIZED.status(), "认证失败，无法访问该资源");
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return (request, response, e) -> {
            LOGGER.error(e.getMessage(), e);
            ServletContextHolder.sendJSON(response,
                    ResultBody.effect(ServiceStatus.FORBIDDEN, "权限不足", null));
            //response.sendError(ServiceStatus.FORBIDDEN.status(), "权限不足");
        };
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            UserPrincipal userPrincipal = tokenService.getPrincipal(request);
            if(!StringUtil.isNull(userPrincipal)){
                tokenService.delToken(userPrincipal.getToken());
            }
            ServletContextHolder.sendJSON(response,
                    ResultBody.effect(ServiceStatus.SUCCEED, "退出成功", null));
        };
    }

}
