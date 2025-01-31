package org.koreait.planitkorea.dto.review.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CreateRequestDto {
    @NotBlank
    private Long productId;

    @NotBlank
    private String reviewCommend;

}
