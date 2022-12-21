package org.shabbydev.securitytest.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

@Entity
@Table(name = "page")
@Getter
@Setter
public class PageEntity {
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
    private String slug;

    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String desc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_entity_id")
    private UserEntity author;
}
