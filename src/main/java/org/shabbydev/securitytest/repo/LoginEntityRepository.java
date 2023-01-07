package org.shabbydev.securitytest.repo;

import org.shabbydev.securitytest.entity.UserEntity;
import org.shabbydev.securitytest.jwt.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginEntityRepository extends JpaRepository<Login, Long> {

    boolean existsByAccessToken(String accessToken);
    Login findByAccessToken(String accessToken);

    List<Login> findAllByUserEntity(UserEntity userEntity);

}
