package com.twitter.domain.service.impl;

import com.twitter.domain.entity.Role;
import com.twitter.domain.entity.User;
import com.twitter.domain.exception.NoContentFoundException;
import com.twitter.domain.exception.UserNotFound;
import com.twitter.domain.service.UserService;
import com.twitter.gateway.repository.UserRepository;
import com.twitter.gateway.rest.datacontract.RegisterDataContract;
import com.twitter.gateway.rest.datacontract.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public User save(RegisterDataContract register) {
        Optional<User> userExists = Optional.of(userRepository.findByUsername(register.getUsername()))
                .or(() -> Optional.of(userRepository.findByEmail(register.getEmail())))
                .orElse(null);

        if (userExists != null)
            throw new IllegalArgumentException("User " + register.getUsername() + " " + register.getEmail() + " already exists");

        var user = mapper.map(register, User.class);
        user.setIsHotUser(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setLastLoginTime(LocalDateTime.now());
        user.setRoles(List.of(Role.builder().id(2L).name("USER").build()));
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return userRepository.save(user);
    }

    @Override
    public List<User> searchByUserName(String username) {
        var users = userRepository.findByUsernameContaining(username);
        if (users.isEmpty()) {
            throw new NoContentFoundException("User not found");
        }
        return users;
    }

    @Override
    @Transactional
    public User updateUser(UUID id, UserDTO requestDTO) {
        log.info("Update user {}", id);
        var userFound = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("User " + id + " not found"));
        userFound.setEmail(requestDTO.getEmail());
        userFound.setIsHotUser(requestDTO.getIsHotUser());
        return userRepository.save(userFound);
    }

    @Override
    public User fetchByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoContentFoundException("User not found"));
    }
}
