package org.koreait.planitkorea.dto.product.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long productId;
    private String productName;
    private String productPrice;
    private String productAddress;
    private String productImage;
    private Long subProductId;
    private String subName;
    private String subPrice;
    private int subPerson;
    private String subProductImage;
}
