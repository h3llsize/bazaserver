package org.shabbydev.securitytest.repo;

import org.shabbydev.securitytest.entity.ObjectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectsEntityRepository extends JpaRepository<ObjectsEntity, Long> {
}
