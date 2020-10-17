package com.itgarden.mapper;

import com.itgarden.dto.UserDto;
import com.itgarden.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationPackage = "mapper.impl")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User userDTOtoUser(UserDto userDto);
    @InheritInverseConfiguration
    UserDto userToUserDTO(User user);

}
