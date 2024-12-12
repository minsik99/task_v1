package com.sparta.task_v1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRequestDto {
    private Long userId;
    private Byte score;
    private String content;
    private String imageUrl;
}
