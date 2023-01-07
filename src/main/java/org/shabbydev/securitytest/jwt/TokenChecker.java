package org.shabbydev.securitytest.jwt;

import org.shabbydev.securitytest.entity.UserEntity;
import org.shabbydev.securitytest.repo.LoginEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TokenChecker {

    @Autowired
    private LoginEntityRepository loginEntityRepository;

    public int getRoleByToken(String token) {
        if(token == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token");

        Login login = loginEntityRepository.findByAccessToken(token);
        if(login == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token");

        return login.getUserEntity().getRoles();
    }

    public UserEntity getUserByToken(String token) {
        if(token == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token");

        Login login = loginEntityRepository.findByAccessToken(token);
        if(login == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token");

        return login.getUserEntity();
    }
}
