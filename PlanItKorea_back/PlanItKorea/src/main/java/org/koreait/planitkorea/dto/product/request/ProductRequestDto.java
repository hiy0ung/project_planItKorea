package org.koreait.planitkorea.dto.product.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    @NotBlank
    private String cityName;

    @NotBlank
    private int person;

    @NotBlank
    private LocalDate startDate;

    @NotBlank
    private LocalDate endDate;
}
