package org.shabbydev.securitytest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;;

@Table
@Entity
@Getter
@Setter
public class EventsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private String description;

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
