package com.ieng.huaimi.core.security.helper;

import com.ieng.huaimi.common.enums.Status;
import com.ieng.huaimi.common.exception.field.ServiceCode;
import com.ieng.huaimi.common.utils.BUtils;
import com.ieng.huaimi.common.utils.string.StringUtil;
import com.ieng.huaimi.common.utils.context.HttpContextUtils;
import com.ieng.huaimi.core.bean.UserAccredit;
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
            HttpContextUtils.sendJSON(response,
                    BUtils.error(ServiceCode.SIGNATURE_NOT_MATCH.getResultCode(),
                            "认证失败，无法访问该资源"));
            //response.sendError(ServiceStatus.UNAUTHORIZED.status(), "认证失败，无法访问该资源");
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return (request, response, e) -> {
            LOGGER.error(e.getMessage(), e);
            HttpContextUtils.sendJSON(response,
                    BUtils.error(ServiceCode.UNDER_AUTHORITY.getResultCode(),
                            ServiceCode.UNDER_AUTHORITY.getResultMsg()));
            //response.sendError(ServiceStatus.FORBIDDEN.status(), "权限不足");
        };
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            UserAccredit userAccredit = tokenService.getPrincipal(request);
            if(!StringUtil.isNull(userAccredit)){
                tokenService.delToken(userAccredit.getToken());
            }
            HttpContextUtils.sendJSON(response,
                    BUtils.error(Status.SUCCEED.status(), "退出成功"));
        };
    }

}
