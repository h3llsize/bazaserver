package org.shabbydev.securitytest.mapper.dto;

import lombok.Data;
import org.shabbydev.securitytest.entity.PageEntity;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private int roles;

    private Set<PageEntity> userPosts;
}
