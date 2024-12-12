package com.sparta.task_v1.dto;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private Long userId;
    private Byte score;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
}
