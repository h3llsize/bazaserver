package org.shabbydev.securitytest.entity;
import javax.persistence.*;;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "post")
@Getter
@Setter
public class PageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String description;

    @Column(nullable = false)
    private Instant createdDate = Instant.now();

    private Instant publishDate;

    @Column(nullable = false)
    private boolean checked = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_entity_id")
    private UserEntity author;
}
