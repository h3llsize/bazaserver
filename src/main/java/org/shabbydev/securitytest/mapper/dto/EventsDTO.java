package org.shabbydev.securitytest.mapper.dto;

import lombok.Data;

@Data
public class EventsDTO {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String date;
    private String place;
    private String programTitle;
    private String programDesc;
    private String coordsX;
    private String coordsY;
    private String startTime;
    private String price;
    private String priceDesc;
}
