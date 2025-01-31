package org.koreait.planitkorea.dto.review.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.planitkorea.entity.Review;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateResponseDto {
    private Long id;

    private Long productId;

    private Long userId;

    private String reviewCommend;

    private Date reviewDate;

    public CreateResponseDto(Review review) {
        this.id = review.getId();
        this.productId = review.getProductId();
        this.userId = review.getUserId();
        this.reviewCommend = review.getReviewCommend();
        this.reviewDate = review.getReviewDate();
    }
}
