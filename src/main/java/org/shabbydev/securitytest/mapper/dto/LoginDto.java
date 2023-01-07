package org.shabbydev.securitytest.mapper.dto;

import lombok.Data;

@Data
public class LoginDto {
    private Long id;
    private String accessToken;
    private String refreshToken;
}
