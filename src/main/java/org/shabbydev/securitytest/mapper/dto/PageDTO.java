package org.shabbydev.securitytest.mapper.dto;

import lombok.Data;
import org.shabbydev.securitytest.entity.UserEntity;

import java.time.Instant;

@Data
public class PageDTO {
    private Long id;
    private String title;
    private String slug;
    private String imageName;
    private String imageUrl;
    private String description;
    private UserEntity author;
    private Instant createdDate;
    private Instant publishDate;
}
