package com.sparta.task_v1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Review")
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Byte score;

    @Column(nullable = false)
    private String content;

    @Column
    private String imageUrl;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // 등록을 위한 엔티티 생성자
    public Review(Product product, Long userId, Byte score, String content, String imageUrl) {
        this.product = product;
        this.userId = userId;
        this.score = score;
        this.content = content;
        this.imageUrl = imageUrl;
    }
}
