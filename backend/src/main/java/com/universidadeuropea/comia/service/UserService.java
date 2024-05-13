package com.universidadeuropea.comia.service;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.universidadeuropea.comia.dto.CredentialsDto;
import com.universidadeuropea.comia.dto.ProfileDto;
import com.universidadeuropea.comia.dto.SignUpDto;
import com.universidadeuropea.comia.dto.UserDto;
import com.universidadeuropea.comia.entity.Profiles;
import com.universidadeuropea.comia.entity.Usuario;
import com.universidadeuropea.comia.exceptions.AppException;
import com.universidadeuropea.comia.mappers.UserMapper;
import com.universidadeuropea.comia.repository.ProfilesRepository;
import com.universidadeuropea.comia.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ProfilesRepository profilesRepository;

    public UserDto login(CredentialsDto credentialsDto) {
        //System.out.println("Email: "+credentialsDto.login());
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

    public UserDto whoami() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = (UserDto) authentication.getPrincipal();

        Usuario usuario = usuarioRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new AppException("Email desconocido", HttpStatus.NOT_FOUND));
        
        if(usuario!=null){
            user.setId(usuario.getId());
            user.setFirstName(usuario.getFirstName());
            return user;
        }
        throw new AppException("Contraseña incorrecta", HttpStatus.BAD_REQUEST);
    }

    public ProfileDto getProfile(UserDto userDto) {

        Optional<Profiles> profileOptional = profilesRepository.findByUserId(userDto.getId());
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(userDto.getId());

        if (profileOptional.isPresent() && usuarioOptional.isPresent()) {
            Profiles profile = profileOptional.get();
            Usuario usuario = usuarioOptional.get();

            return new ProfileDto(
                usuario.getFirstName(),
                usuario.getLastName(),
                profile.getId(),
                profile.getUserId(),
                profile.getIntolerances(),
                profile.getDiets()
            );
        }

        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            return new ProfileDto(
                usuario.getFirstName(),
                usuario.getLastName()
            );
        }
        throw new AppException("Perfil no encontrado", HttpStatus.NOT_FOUND);
    }

    public ProfileDto saveProfile(Long id, ProfileDto profileDto) {

        
        Usuario savedUser = null;
        Profiles savedProfile = null;
        
        Optional<Profiles> profileOptional = profilesRepository.findByUserId(id);
        if(profileOptional.isPresent()){
            Profiles profile = profileOptional.get();
            profile.setUserId(id);
            profile.setIntolerances(profileDto.getIntolerances());
            profile.setDiets(profileDto.getDiets());
            
            savedProfile = profilesRepository.save(profile);
        } else {
            Profiles profile = new Profiles();
            profile.setUserId(id);
            profile.setIntolerances(profileDto.getIntolerances());
            profile.setDiets(profileDto.getDiets());
            
            savedProfile = profilesRepository.save(profile);
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario user = usuarioOptional.get();
            user.setFirstName(profileDto.getFirstName());
            user.setLastName(profileDto.getLastName());
            savedUser=usuarioRepository.save(user);
        }

        if (savedProfile!=null && savedUser!=null) {

            return new ProfileDto(
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedProfile.getId(),
                savedProfile.getUserId(),
                savedProfile.getIntolerances(),
                savedProfile.getDiets()
            );
        }

        throw new AppException("Perfil no encontrado", HttpStatus.NOT_FOUND);
    }



}


