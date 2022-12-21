package org.shabbydev.securitytest.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
public class EventsEntity {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String desc;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String programTitle;

    @Column(nullable = false)
    private String programDesc;

    @Column(nullable = false)
    private String coordsX;

    @Column(nullable = false)
    private String coordsY;

    @Column(nullable = false)
    private String startTime;

    private String price;

    private String priceDesc;
}
