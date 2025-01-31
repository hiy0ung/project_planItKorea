package org.koreait.planitkorea.dto.reservation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationRequestDto {
    @NotBlank
    private Long productId;

    @NotBlank
    private Long subProductId;

    @NotBlank
    private Long person;

    @NotBlank
    private String totalPrice;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;
}

