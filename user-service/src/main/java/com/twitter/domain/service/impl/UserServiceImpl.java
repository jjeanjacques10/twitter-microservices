package com.twitter.domain.service.impl;

import com.twitter.domain.entity.User;
import com.twitter.domain.service.UserService;
import com.twitter.gateway.repository.UserRepository;
import com.twitter.gateway.rest.datacontract.AdminResponseDTO;
import com.twitter.gateway.rest.datacontract.UserRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveAdmin(UserRequestDTO requestDTO) {
        log.info("save user " + requestDTO.toString());
    }

    @Override
    public AdminResponseDTO searchAdmin(UserRequestDTO requestDTO) {
        return null;
    }

    @Override
    public User updateAdmin(UserRequestDTO requestDTO) {
        return null;
    }

    @Override
    public User fetchAdminByUsername(String username) {
        return null;
    }
}
