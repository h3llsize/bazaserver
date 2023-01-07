package org.shabbydev.securitytest.jwt;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.shabbydev.securitytest.entity.UserEntity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
@Getter
@Setter
public class Login {
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

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    @Column(unique = true)
    private String accessToken;

    private Instant createdDate;
}
