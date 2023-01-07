package org.shabbydev.securitytest.controller;

import org.shabbydev.securitytest.mapper.dto.LoginDto;
import org.shabbydev.securitytest.mapper.dto.UserDTO;
import org.shabbydev.securitytest.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<String> signUp(@RequestBody UserDTO userDTO) {
        return authService.signUp(userDTO);
    }

    @PostMapping("login")
    public LoginDto login(@RequestBody UserDTO userDTO) {
        return authService.login(userDTO);
    }

    @GetMapping("validate")
    public UserDTO validate(@RequestParam String token) {
        return authService.validate(token);
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("accessToken");
        return authService.logout(token);
    }

}
