package org.shabbydev.securitytest.repo;

import org.shabbydev.securitytest.entity.PageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PageEntityRepository extends JpaRepository<PageEntity, Long> {

    @Query("select p from PageEntity p " +
            " where p.publishDate is not null and " +
            " p.checked = true " +
            "order by p.publishDate desc ")
    Page<PageEntity> findAll(Pageable pageable);

    @Query("select p from PageEntity p " +
            " where p.publishDate is null and " +
            " p.checked = false " +
            "order by p.publishDate desc ")
    Page<PageEntity> findAllForModerate(Pageable pageable);

    @Query("select p from PageEntity p " +
            "where (p.publishDate is not null and" +
            " p.checked = true) and " +
            " (lower(p.title) LIKE :query " +
            "or lower(p.description) LIKE :query) " +
            "order by p.publishDate desc")
    Page<PageEntity> findByQuery(Pageable pageable, @Param("query") String query);
}
