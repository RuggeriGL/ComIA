package com.universidadeuropea.comia.controller;

import java.net.URI;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.universidadeuropea.comia.config.UserAuthProvider;
import com.universidadeuropea.comia.dto.CredentialsDto;
import com.universidadeuropea.comia.dto.SignUpDto;
import com.universidadeuropea.comia.dto.UserDto;
import com.universidadeuropea.comia.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
        UserDto user = userService.login(credentialsDto);
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto singUpDto) {
        UserDto userDto = userService.register(singUpDto);
        userDto.setToken(userAuthProvider.createToken(userDto));
        return ResponseEntity.created(URI.create("/users/" + userDto.getId())).body(userDto);
    }
}
