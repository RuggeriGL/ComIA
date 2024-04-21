package com.universidadeuropea.comia.mappers;

import org.mapstruct.Mapper;
import com.universidadeuropea.comia.dto.UserDto;
import com.universidadeuropea.comia.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(Usuario usuario);
    
}
