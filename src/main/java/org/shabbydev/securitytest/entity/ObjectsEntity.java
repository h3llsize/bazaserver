package org.shabbydev.securitytest.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
public class ObjectsEntity {
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
    private String place;

    @Column(nullable = false)
    private String aboutTitle;

    @Column(nullable = false)
    private String aboutDesc;

    @Column(nullable = false)
    private String coordsX;

    @Column(nullable = false)
    private String coordsY;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String endTime;
}
