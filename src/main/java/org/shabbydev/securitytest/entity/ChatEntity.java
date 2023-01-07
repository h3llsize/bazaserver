package org.shabbydev.securitytest.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "message")
@Entity
@Getter
@Setter
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String message;

    private Instant sendTime;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

}
