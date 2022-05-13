package com.twitter.domain.service;

import com.twitter.domain.entity.User;
import com.twitter.gateway.rest.datacontract.AdminResponseDTO;
import com.twitter.gateway.rest.datacontract.UserRequestDTO;

public interface UserService {

    void saveAdmin(UserRequestDTO requestDTO);

    AdminResponseDTO searchAdmin(UserRequestDTO requestDTO);

    User updateAdmin(UserRequestDTO requestDTO);

    User fetchAdminByUsername(String username);
}
