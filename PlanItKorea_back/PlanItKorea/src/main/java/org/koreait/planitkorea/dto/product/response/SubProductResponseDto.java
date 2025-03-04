package org.koreait.planitkorea.dto.product.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubProductResponseDto {
    private Long subProductId;
    private String subName;
    private String subPrice;
    private int subPerson;
    private List<String> subProductImages;
}
