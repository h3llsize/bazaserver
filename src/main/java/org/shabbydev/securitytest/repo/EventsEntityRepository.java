package org.shabbydev.securitytest.repo;


import org.shabbydev.securitytest.entity.EventsEntity;
import org.shabbydev.securitytest.entity.PageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsEntityRepository extends JpaRepository<EventsEntity, Long> {
    Page<EventsEntity> findAll(Pageable pageable);

    Page<EventsEntity> findAllByTitle(Pageable pageable, String title);
}
