package com.umc.domain.uuid.repository;

import com.umc.domain.uuid.entity.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
}
