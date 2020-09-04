package com.ieng.huaimi.web.controller.common;

import com.ieng.huaimi.common.bean.LoginForm;
import com.ieng.huaimi.common.bean.ResultBody;
import com.ieng.huaimi.common.enums.Status;
import com.ieng.huaimi.common.exception.field.ServiceCode;
import com.ieng.huaimi.common.utils.BUtils;
import com.ieng.huaimi.core.bean.UserAccredit;
import com.ieng.huaimi.core.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    public static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResultBody doLogin(@RequestBody @Validated LoginForm loginForm) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

            UserAccredit userAccredit = (UserAccredit) authentication.getPrincipal();

            Map<String, String> map = new HashMap<>();
            map.put("token", tokenService.createToken(userAccredit));
            return BUtils.data(map);
        } catch (BadCredentialsException e) {
            LOGGER.error(e.getMessage(), e);
            return BUtils.error(ServiceCode.USER_PASSWORD_ERROR.getResultCode(),
                    ServiceCode.USER_PASSWORD_ERROR.getResultMsg());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BUtils.error(ServiceCode.LOGIN_ERROR.getResultCode(),
                    ServiceCode.LOGIN_ERROR.getResultMsg());
        }
    }


}
