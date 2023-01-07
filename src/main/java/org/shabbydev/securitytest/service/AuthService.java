package org.shabbydev.securitytest.service;

import org.shabbydev.securitytest.entity.UserEntity;
import org.shabbydev.securitytest.exception.EmailNotFoundException;
import org.shabbydev.securitytest.jwt.Login;
import org.shabbydev.securitytest.jwt.Token;
import org.shabbydev.securitytest.jwt.TokenChecker;
import org.shabbydev.securitytest.mapper.custom.LoginMapper;
import org.shabbydev.securitytest.mapper.custom.UserMapper;
import org.shabbydev.securitytest.mapper.dto.LoginDto;
import org.shabbydev.securitytest.mapper.dto.UserDTO;
import org.shabbydev.securitytest.repo.LoginEntityRepository;
import org.shabbydev.securitytest.repo.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private LoginEntityRepository loginEntityRepository;

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenChecker tokenChecker;

    public ResponseEntity<String> signUp(UserDTO userDTO) {
        String MD5pass = MD5(userDTO.getPassword());

        if(userEntityRepository.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Данная почта уже зарегистирована");
        }
        userDTO.setRoles(0);
        userDTO.setPassword(MD5pass);
        userEntityRepository.save(userMapper.toEntity(userDTO));
        return ResponseEntity.ok("Вы успешно зарегистрировались");
    }

    public LoginDto login(UserDTO userDTO) {
        UserEntity userEntity = userEntityRepository.findByEmail(userDTO.getEmail()).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid auth"));

        if(!Objects.equals(userEntity.getPassword(), MD5(userDTO.getPassword()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid auth");
        }


        Login login = new Login();
        login.setAccessToken(Token.of(userEntity.getId(), 10L, "secret_key_256_ahahahahahhahaha_you_never_lalala").getToken());
        login.setUserEntity(userEntity);
        login.setCreatedDate(Instant.now());

        loginEntityRepository.save(login);

        return loginMapper.toDto(login);
    }

    public ResponseEntity<String> logout(String token) {
        loginEntityRepository.delete(loginEntityRepository.findByAccessToken(token));

        return ResponseEntity.ok("Вы успешно вышли со всех устройств");
    }

    public UserDTO validate(String token) {

        Login login = loginEntityRepository.findByAccessToken(token);

        if(!loginEntityRepository.existsByAccessToken(token))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token");

        if(Instant.now().isAfter(login.getCreatedDate().plus(7, ChronoUnit.DAYS))) {
            loginEntityRepository.delete(login);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token expired");
        }
        return userMapper.toDto(loginEntityRepository.findByAccessToken(token).getUserEntity());
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException ignored) {
        }
        return null;
    }
}
