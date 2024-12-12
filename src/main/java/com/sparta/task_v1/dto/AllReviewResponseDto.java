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
}
