package com.sparta.task_v1.repository;

import com.sparta.task_v1.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByProductIdAndUserId(Long productId, Long userId);

    // 커서를 기준으로 다음 페이지 조회
    @Query("SELECT r FROM Review r WHERE r.product.id = :productId AND r.id <= :cursor ORDER BY r.id DESC")
    List<Review> findReviewsByProductIdAndCursor(@Param("productId") Long productId, @Param("cursor") Long cursor, Pageable pageable);
}