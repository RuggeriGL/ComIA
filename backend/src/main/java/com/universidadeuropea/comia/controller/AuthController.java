package com.universidadeuropea.comia.controller;

import java.net.URI;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.universidadeuropea.comia.config.UserAuthProvider;
import com.universidadeuropea.comia.dto.CredentialsDto;
import com.universidadeuropea.comia.dto.ProfileDto;
import com.universidadeuropea.comia.dto.SignUpDto;
import com.universidadeuropea.comia.dto.UserDto;
import com.universidadeuropea.comia.entity.Usuario;
import com.universidadeuropea.comia.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
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
        //System.out.println("USER TOKEN: "+user.getToken());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto singUpDto) {
        UserDto userDto = userService.register(singUpDto);
        userDto.setToken(userAuthProvider.createToken(userDto));
        //System.out.println("USER TOKEN: "+userDto.getToken());
        return ResponseEntity.created(URI.create("/users/" + userDto.getId())).body(userDto);
    }

    @GetMapping("/whoami")
    public ResponseEntity<UserDto> whoAmI() {
        UserDto userDto = userService.whoami();

        if(userDto!=null && userDto.getId()!=null){
            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.badRequest().body(userDto);
    }

    
}
