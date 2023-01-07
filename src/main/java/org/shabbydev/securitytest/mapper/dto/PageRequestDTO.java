package org.shabbydev.securitytest.mapper.dto;

import lombok.Data;
import org.shabbydev.securitytest.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@Data
public class PageRequestDTO {
    private String title;
    private String slug;
    private String imageName;
    private String description;
    private String accessToken;
    private MultipartFile file;
}
