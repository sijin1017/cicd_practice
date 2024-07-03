package com.umc.domain.heart.repository;

import com.umc.domain.heart.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByBoardIdAndUserId(Long boardId, Long userId);
    int countByBoardId(Long boardId);
}
