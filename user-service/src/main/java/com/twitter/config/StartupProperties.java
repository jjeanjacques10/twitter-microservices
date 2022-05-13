package com.twitter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "admin")
public class StartupProperties {

    private String id;

    private String username;

    private String email;

    private String password;

    private boolean isHotUser;

}
