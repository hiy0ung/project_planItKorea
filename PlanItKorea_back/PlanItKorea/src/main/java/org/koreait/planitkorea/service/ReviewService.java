package org.koreait.planitkorea.service;

import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.review.request.CreateRequestDto;
import org.koreait.planitkorea.dto.review.response.CreateResponseDto;
import org.koreait.planitkorea.dto.review.response.ProductReviewResponseDto;

import java.util.List;

public interface ReviewService {
    ResponseDto<CreateResponseDto> createReview(Long userId, CreateRequestDto dto);

    ResponseDto<List<ProductReviewResponseDto>> getProductReviews(Long productId);

    ResponseDto<List<ProductReviewResponseDto>> getMyReviews(Long userId);

    ResponseDto<Boolean> deleteReview(Long userId, Long reviewId);
}
