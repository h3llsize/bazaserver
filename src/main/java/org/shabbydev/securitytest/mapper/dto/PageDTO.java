package org.shabbydev.securitytest.mapper.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.shabbydev.securitytest.entity.UserEntity;

@Data
public class PageDTO {
    private String title;
    private String slug;
    private String imageName;
    private String imageUrl;
    private String desc;
    private UserEntity author;
}
