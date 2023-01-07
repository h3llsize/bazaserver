package org.shabbydev.securitytest.mapper.dto;

import lombok.Data;

@Data
public class ChatRequestDTO {

    private String message;

    private String accessToken;

}
