package org.shabbydev.securitytest.controller;

import org.shabbydev.securitytest.mapper.dto.UserDTO;
import org.shabbydev.securitytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public UserDTO findUserById(String id) {
        return userService.findUserById(Long.valueOf(id));
    }
}
