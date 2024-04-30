package com.universidadeuropea.comia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.universidadeuropea.comia.dto.SignUpDto;
import com.universidadeuropea.comia.dto.UserDto;
import com.universidadeuropea.comia.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(Usuario usuario);

    @Mapping(target = "password", ignore = true)
    Usuario signUpToUser(SignUpDto signUpDto);
    
}
