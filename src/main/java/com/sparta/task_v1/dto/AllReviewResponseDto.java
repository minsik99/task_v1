package com.sparta.task_v1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AllReviewResponseDto {
    private Long totalCount;
    private float score;
    private Long cursor;
    private List<ReviewResponseDto> reviews;

    public AllReviewResponseDto(long totalCount, float averageScore, Long cursor, List<ReviewResponseDto> reviewResponseDtoList) {
        this.totalCount = totalCount;
        this.score = averageScore;
        this.cursor = cursor;
        this.reviews = reviewResponseDtoList;
    }
}
