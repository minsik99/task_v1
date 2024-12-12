package com.sparta.task_v1.service;

import com.sparta.task_v1.dto.AllReviewResponseDto;
import com.sparta.task_v1.dto.ReviewRequestDto;
import com.sparta.task_v1.dto.ReviewResponseDto;
import com.sparta.task_v1.entity.Product;
import com.sparta.task_v1.entity.Review;
import com.sparta.task_v1.repository.ProductRepository;
import com.sparta.task_v1.repository.ReviewRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    // 리뷰 조회
    @Transactional(readOnly = true)
    public AllReviewResponseDto getReviews(Long productId, Long cursor, int size) {

        // 상품 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        // 페이징 처리
        Pageable pageable = PageRequest.of(0, size);
        List<Review> reviews;
        reviews = reviewRepository.findReviewsByProductIdAndCursor(productId, cursor, pageable);

        // 총 리뷰 수와 평균 점수
        long totalCount = product.getReviewCount();
        float averageScore = product.getScore();

        // 리뷰 응답 DTO 변환
        List<ReviewResponseDto> reviewResponseDtoList = reviews.stream()
                .map(review -> new ReviewResponseDto(
                        review.getId(),
                        review.getUserId(),
                        review.getScore(),
                        review.getContent(),
                        review.getImageUrl(),
                        review.getCreatedAt()))
                .toList();

        // 전체 응답 반환
        return new AllReviewResponseDto(totalCount, averageScore, cursor, reviewResponseDtoList);
    }


    // 리뷰 등록
    @Transactional
    public void createReview(Long productId, ReviewRequestDto requestDto, MultipartFile image) {

        // 상품 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        // 이미 사용자가 상품에 리뷰를 남겼다면
        if (reviewRepository.existsByProductIdAndUserId(productId, requestDto.getUserId())) {
            throw new IllegalStateException("해당 상품에 이미 리뷰를 작성하였습니다.");
        }

        // 이미지 처리
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            imageUrl = uploadToS3(image);
        }

        // DB insert
        Review review = new Review(product, requestDto.getUserId(), requestDto.getScore(),
                requestDto.getContent(), imageUrl);
        reviewRepository.save(review);

        // 제품의 리뷰 수와 점수 평균 업데이트
        long reviewCount = product.getReviewCount();
        long updatedCount = reviewCount + 1;
        float score = product.getScore();
        float updatedScore = (score * reviewCount + requestDto.getScore()) / updatedCount;

        product.updateReviewAndCount(updatedCount, updatedScore);
    }

    // 더미로 S3 업로드 후 url을 반환할 메서드
    private String uploadToS3(MultipartFile image) {
        String fileName = image.getOriginalFilename();
        return "https://amazon-s3-url.com/" + fileName;
    }
}
