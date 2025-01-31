package org.koreait.planitkorea.repository;

import jakarta.transaction.Transactional;
import org.koreait.planitkorea.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r, pi.productImage, p.productName " +
            "FROM Reservation r " +
            "JOIN r.product p " +
            "JOIN ProductImage pi ON  p.id = pi.product.id " +
            "WHERE r.user.id = :userId")
    List<Object[]> findAllByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Reservation r SET r.reservationStatus = 2 WHERE r.reservationStatus = 1 AND r.endDate < current_date")
    void updateExpiredReservations();
}

