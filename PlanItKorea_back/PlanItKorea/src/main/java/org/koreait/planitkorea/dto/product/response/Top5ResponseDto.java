package org.koreait.planitkorea.dto.product.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Top5ResponseDto {

    private Long productId;

    private String productName;

    private String productCategory;

    private String productRegion;

    private String productAddress;

    private String productPrice;

    private String productImage;
}