package org.shabbydev.securitytest.mapper.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ChatDTO {
    private Long id;

    private String message;

    private Instant sendTime;

    private UserDTO author;

    private String accessToken;
}
