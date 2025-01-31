package org.koreait.planitkorea.dto.wishList.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListResponseDto {
    private Long userId;
    private Long productId;
    private String productName;
    private String productAddress;
    private String productPrice;
    private String productImage;
}
