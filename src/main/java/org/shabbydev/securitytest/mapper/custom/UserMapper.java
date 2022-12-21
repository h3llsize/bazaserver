package org.shabbydev.securitytest.mapper.custom;

import org.mapstruct.Mapper;
import org.shabbydev.securitytest.entity.UserEntity;
import org.shabbydev.securitytest.mapper.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(UserEntity userEntity);

    UserEntity toEntity(UserDTO userDTO);
}
