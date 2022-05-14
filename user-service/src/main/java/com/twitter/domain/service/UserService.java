package com.twitter.domain.service;

import com.twitter.domain.entity.User;
import com.twitter.gateway.rest.datacontract.RegisterDataContract;
import com.twitter.gateway.rest.datacontract.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User save(RegisterDataContract register);

    List<User> searchByUserName(String username);

    User updateUser(UUID id, UserDTO requestDTO);

    User fetchByUsername(String username);
}
