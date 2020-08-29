package com.ieng.huaimi.web.controller.common;

import com.ieng.huaimi.common.domain.LoginBody;
import com.ieng.huaimi.common.domain.ResultBody;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    public static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/login")
    public ResultBody doLogin(@RequestBody @Validated LoginBody loginBody) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword()));

            UserAccredit userAccredit = (UserAccredit) authentication.getPrincipal();

            return ResultBody.succeed(tokenService.createToken(userAccredit));
        } catch (BadCredentialsException e) {
            LOGGER.error(e.getMessage(), e);
            return ResultBody.failed("用户名或密码错误");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultBody.failed("登录失败");
        }
    }


}
