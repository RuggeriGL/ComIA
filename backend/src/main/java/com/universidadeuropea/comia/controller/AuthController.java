package com.universidadeuropea.comia.controller;

import java.net.URI;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
        UserDto user = userService.login(credentialsDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto singUpDto) {
        UserDto userDto = userService.register(singUpDto);
        return ResponseEntity.created(URI.create("/users/" + userDto.getId())).body(userDto);
    }
}
