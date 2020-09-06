package com.ieng.huaimi.web.controller.common;

import com.ieng.huaimi.common.view.LoginForm;
import com.ieng.huaimi.common.view.ResultBody;
import com.ieng.huaimi.common.utils.BUtils;
import com.ieng.huaimi.core.bean.UserAccredit;
import com.ieng.huaimi.core.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResultBody doLogin(@RequestBody @Validated LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

        UserAccredit userAccredit = (UserAccredit) authentication.getPrincipal();

        Map<String, String> map = new HashMap<>(1);
        map.put("token", tokenService.createToken(userAccredit));
        return BUtils.data(map);
    }


}
