package org.koreait.planitkorea.dto.product.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponseDto {
    private Long productId;
    private String productCategory;
    private String productName;
    private String productPrice;
    private String productAddress;
    private String productDescription;
    private List<String> productImages;
    private List<SubProductResponseDto> subProducts = new ArrayList<>();
    private List<FacilityResponseDto> facilities = new ArrayList<>();
}
