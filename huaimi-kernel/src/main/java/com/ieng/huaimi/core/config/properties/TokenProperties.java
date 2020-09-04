package com.ieng.huaimi.core.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@Component
@ConfigurationProperties(prefix = "jwt")
@Validated
public class TokenProperties {
    @NotEmpty
    private String secret;
    @NotNull
    private Duration expiration;

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setExpiration(Duration expiration) {
        this.expiration = expiration;
    }

    public String getSecret() {
        return secret;
    }

    public Duration getExpiration() {
        return expiration;
    }

}
