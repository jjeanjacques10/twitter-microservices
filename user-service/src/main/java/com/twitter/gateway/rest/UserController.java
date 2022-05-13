package com.twitter.gateway.rest;

import com.twitter.domain.service.UserService;
import com.twitter.gateway.rest.datacontract.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity saveAdmin(@RequestBody UserRequestDTO requestDTO) {
        userService.saveAdmin(requestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/search")
    public ResponseEntity searchAdmin(@RequestBody UserRequestDTO requestDTO) {
        return ResponseEntity.ok().body(userService.searchAdmin(requestDTO));
    }

    @PutMapping
    public ResponseEntity updateAdmin(@RequestBody UserRequestDTO requestDTO) {
        return ResponseEntity.ok().body(userService.updateAdmin(requestDTO));
    }

    @GetMapping("/fetch-admin/{username}")
    public ResponseEntity fetchAdminByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.fetchAdminByUsername(username));
    }

}
