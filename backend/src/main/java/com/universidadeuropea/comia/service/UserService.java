package com.universidadeuropea.comia.service;

import java.nio.CharBuffer;
import java.util.Date;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.universidadeuropea.comia.dto.CredentialsDto;
import com.universidadeuropea.comia.dto.SignUpDto;
import com.universidadeuropea.comia.dto.UserDto;
import com.universidadeuropea.comia.entity.Usuario;
import com.universidadeuropea.comia.exceptions.AppException;
import com.universidadeuropea.comia.mappers.UserMapper;
import com.universidadeuropea.comia.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto) {
        System.out.println("Email: "+credentialsDto.login());
        Usuario usuario = usuarioRepository.findByEmail(credentialsDto.login())
                .orElseThrow(() -> new AppException("Email desconocido", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), usuario.getPassword())) {
            return userMapper.toUserDto(usuario);
        }
        throw new AppException("Contraseña incorrecta", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto signUpDto) {
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(signUpDto.email());

        if(optUsuario.isPresent()){
            throw new AppException("El email ya esta registrado", HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = userMapper.signUpToUser(signUpDto);
        usuario.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
        usuario.setEnabled(true);
        usuario.setFechaCreacion(new Date());
        Usuario savedUser = usuarioRepository.save(usuario);

        
        return userMapper.toUserDto(savedUser);

    }
}


