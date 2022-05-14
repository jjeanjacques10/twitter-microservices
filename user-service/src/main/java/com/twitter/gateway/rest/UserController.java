package com.twitter.gateway.rest;

import com.twitter.domain.entity.User;
import com.twitter.domain.service.UserService;
import com.twitter.gateway.rest.datacontract.RegisterDataContract;
import com.twitter.gateway.rest.datacontract.ResponseDataContract;
import com.twitter.gateway.rest.datacontract.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseDataContract> createUser(@RequestBody RegisterDataContract register) {
        User userCreated = userService.save(register);
        return ResponseEntity.created(URI.create("/" + userCreated.getId()))
                .body(ResponseDataContract.builder()
                        .data(mapper.map(userCreated, UserDTO.class))
                        .build());
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam(name = "username", defaultValue = "") String username) {
        var users = userService.searchByUserName(username.toLowerCase(Locale.ROOT));
        var usersDto = users.stream().map(u -> mapper.map(u, UserDTO.class));
        return ResponseEntity.ok(ResponseDataContract.builder()
                .data(usersDto)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable UUID id,
                                     @RequestBody UserDTO requestDTO) {
        User user = userService.updateUser(id, requestDTO);
        return ResponseEntity.ok(ResponseDataContract.builder()
                .data(mapper.map(user, UserDTO.class))
                .build());
    }

    @GetMapping("/fetch/{username}")
    public ResponseEntity<ResponseDataContract> fetchByUsername(@PathVariable("username") String username) {
        User user = userService.fetchByUsername(username);
        return ResponseEntity.ok(ResponseDataContract.builder()
                .data(mapper.map(user, UserDTO.class))
                .build());
    }

}
