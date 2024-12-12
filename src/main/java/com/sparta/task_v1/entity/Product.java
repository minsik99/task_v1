package com.sparta.task_v1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Product")
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long reviewCount;

    @Column(nullable = false)
    private float score;

    public void updateReviewAndCount(long updatedCount, float updatedScore) {
        this.reviewCount = updatedCount;
        this.score = updatedScore;
    }
}
