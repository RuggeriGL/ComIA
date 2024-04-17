package com.universidadeuropea.comia.service;

import java.nio.CharBuffer;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.universidadeuropea.comia.dto.CredentialsDto;
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
        Usuario usuario = usuarioRepository.findByEmail(credentialsDto.email())
                .orElseThrow(() -> new AppException("Email desconocido", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), usuario.getPassword())) {
            return userMapper.toUserDto(usuario);
        }
        throw new AppException("Contraseña incorrecta", HttpStatus.BAD_REQUEST);
    }
}
