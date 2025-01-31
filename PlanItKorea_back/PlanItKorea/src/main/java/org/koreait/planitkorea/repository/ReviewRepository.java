package org.koreait.planitkorea.repository;

import jakarta.validation.constraints.NotBlank;
import org.koreait.planitkorea.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r, u.userId FROM Review r JOIN User u ON r.userId = u.id WHERE r.productId = :productId")
    List<Object[]> findReviewsWithUserIdByProductId(@Param("productId") Long productId);

   @Query("SELECT r, u.userId FROM Review r JOIN User u ON r.userId = u.id WHERE r.userId = :userId")
    List<Object[]> findReviewsWithUserIdByUserId(@Param("userId") Long userId);
}
