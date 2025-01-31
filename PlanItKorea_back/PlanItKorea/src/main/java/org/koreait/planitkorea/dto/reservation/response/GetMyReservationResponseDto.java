package org.koreait.planitkorea.dto.reservation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMyReservationResponseDto {
    private Long id;

    private Long userId;

    private Long productId;

    private Long subProductId;

    private Long person;

    private String totalPrice;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int reservationStatus;

    private String productImage;

    private String productName;
}
