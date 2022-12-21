package org.shabbydev.securitytest.service;

import org.shabbydev.securitytest.mapper.custom.UserMapper;
import org.shabbydev.securitytest.mapper.dto.UserDTO;
import org.shabbydev.securitytest.repo.UserEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserEntityRepository userEntityRepository;

    public UserService(UserMapper userMapper, UserEntityRepository userEntityRepository) {
        this.userMapper = userMapper;
        this.userEntityRepository = userEntityRepository;
    }

    public UserDTO findUserById(Long userId) {
        return userEntityRepository.findById(userId).map(userMapper::toDto).orElse(null);
    }

}
