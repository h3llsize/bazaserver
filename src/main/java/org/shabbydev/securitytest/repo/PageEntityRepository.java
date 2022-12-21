package org.shabbydev.securitytest.repo;

import org.shabbydev.securitytest.entity.PageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageEntityRepository extends JpaRepository<PageEntity, Long> {
    Page<PageEntity> findAll(Pageable pageable);

    Page<PageEntity> findAllByTitle(Pageable pageable, String title);
}
