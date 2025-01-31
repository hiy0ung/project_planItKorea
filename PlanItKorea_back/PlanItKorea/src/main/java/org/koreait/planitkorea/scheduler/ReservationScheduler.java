package org.koreait.planitkorea.scheduler;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.repository.ReservationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationScheduler {

    private final ReservationRepository reservationRepository;

    @Scheduled(cron = "0 0 12 * * ?")
    public void reservationStatusChange() {
        reservationRepository.updateExpiredReservations();
    }
}
