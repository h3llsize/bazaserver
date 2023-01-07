package org.shabbydev.securitytest.mapper.custom;

import org.mapstruct.Mapper;
import org.shabbydev.securitytest.jwt.Login;
import org.shabbydev.securitytest.mapper.dto.LoginDto;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    Login toEntity(LoginDto loginDto);
    LoginDto toDto(Login login);
}
