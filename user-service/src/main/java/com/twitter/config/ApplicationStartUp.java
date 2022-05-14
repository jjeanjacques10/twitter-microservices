package com.twitter.config;

import com.twitter.domain.entity.Role;
import com.twitter.domain.entity.User;
import com.twitter.gateway.repository.RoleRepository;
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
    public CommandLineRunner loadData(UserRepository userRepository, RoleRepository roleRepository) {
        return (args) -> {
            List<User> users = userRepository.findAll();

            if (ObjectUtils.isEmpty(users)) {
                roleRepository.save(saveRole(1L, "ADMIN"));
                roleRepository.save(saveRole(2L, "USER"));
                userRepository.save(saveUser());
            }
        };
    }

    public Role saveRole(Long id, String name) {
        return new Role().builder()
                .id(id)
                .name(name)
                .build();
    }

    public User saveUser() {
        Role role = new Role().builder()
                .id(1L)
                .name("ADMIN")
                .build();

        return User.builder()
                .username(startupProperties.getUsername())
                .email(startupProperties.getEmail())
                .password(BCrypt.hashpw(startupProperties.getPassword(), BCrypt.gensalt()))
                .createdAt(LocalDateTime.now())
                .lastLoginTime(LocalDateTime.now())
                .isHotUser(true)
                .roles(Arrays.asList(role)).build();
    }
}
