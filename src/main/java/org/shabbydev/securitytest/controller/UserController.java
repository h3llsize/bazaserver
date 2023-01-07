package org.shabbydev.securitytest.controller;

import org.shabbydev.securitytest.mapper.dto.UserDTO;
import org.shabbydev.securitytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user/")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public UserDTO findUserById(@PathVariable String id) {
        return userService.findUserById(Long.valueOf(id));
    }
}
