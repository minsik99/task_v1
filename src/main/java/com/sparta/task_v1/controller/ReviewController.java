package com.sparta.task_v1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.task_v1.dto.AllReviewResponseDto;
import com.sparta.task_v1.dto.ReviewRequestDto;
import com.sparta.task_v1.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 리뷰 조회
    @GetMapping("/{productId}/reviews")
    public ResponseEntity<AllReviewResponseDto> getReviews(@PathVariable Long productId,
                                                        @RequestParam(value = "cursor", required = false) Long cursor,
                                                        @RequestParam(value = "size", defaultValue = "10") int size) {

        AllReviewResponseDto responseDto = reviewService.getReviews(productId, cursor, size);
        return ResponseEntity.ok(responseDto);
    }

    // 리뷰 등록
    @PostMapping(value = "/{productId}/reviews", consumes = "multipart/form-data")
    public ResponseEntity<Void> createReview(@PathVariable Long productId,
                                             @RequestPart("review") String reviewJson,
                                             @RequestPart(value = "image", required = false) MultipartFile image) {
        // JSON 문자열을 DTO로 변환
        ReviewRequestDto requestDto = parseJsonToDto(reviewJson);

        // 서비스 호출
        reviewService.createReview(productId, requestDto, image);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private ReviewRequestDto parseJsonToDto(String reviewJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(reviewJson, ReviewRequestDto.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid JSON format for 'review'");
        }
    }
}
