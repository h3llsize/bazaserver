package org.shabbydev.securitytest.mapper.dto;

import lombok.Data;

@Data
public class ObjectsDTO {
    private Long id;
    private String title;

    private String description;

    private String imageUrl;

    private String place;

    private String aboutTitle;

    private String aboutDesc;

    private String coordsX;

    private String coordsY;

    private String startTime;

    private String endTime;
}
