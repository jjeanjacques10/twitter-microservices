package com.twitter.config;

import com.twitter.domain.entity.Role;
import com.twitter.domain.entity.User;
import com.twitter.gateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class ApplicationStartUp {

    @Autowired
    private StartupProperties startupProperties;

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository) {
        return (args) -> {
            List<User> users = userRepository.findAll();

            if (ObjectUtils.isEmpty(users))
                userRepository.save(saveUser());
        };
    }

    public User saveUser() {
        Role role = new Role().builder()
                .id(1L)
                .name("ADMIN")
                .build();

        User user = new User();
        user.setUsername(startupProperties.getUsername());
        user.setEmail(startupProperties.getEmail());
        user.setPassword(BCrypt.hashpw(startupProperties.getPassword(), BCrypt.gensalt()));
        user.setCreatedAt(LocalDateTime.now());
        user.setLastLoginTime(LocalDateTime.now());
        user.setIsHotUser(true);
        user.setRoles(Arrays.asList(role));

        return user;
    }
}
