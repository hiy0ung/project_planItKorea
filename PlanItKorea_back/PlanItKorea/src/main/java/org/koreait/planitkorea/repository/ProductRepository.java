package org.koreait.planitkorea.repository;

import org.koreait.planitkorea.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 전체 조회
    @Query(value = """
      WITH Available_Rooms AS (
           SELECT
               p.id AS product_id,
               p.product_category,
               p.product_name,
               p.product_price,
               p.product_address,
               p.product_description,
               (SELECT pi.product_image
                FROM Product_images pi
                WHERE pi.product_id = p.id
                ORDER BY pi.id ASC
                LIMIT 1) AS product_image
           FROM Products p
           INNER JOIN Product_Cities pc ON pc.product_id = p.id
           INNER JOIN Cities c ON c.id = pc.city_id
           INNER JOIN Sub_Products sp ON sp.main_product_id = p.id
           WHERE c.city_name = :cityName
           AND sp.sub_person >= :person
           AND NOT EXISTS (
               SELECT 1
               FROM Reservations r
               WHERE r.sub_product_id = sp.id
               AND r.start_date <= :endDate
               AND r.end_date >= :startDate
           )
       )
       SELECT
           ar.product_id,
           ar.product_category,
           ar.product_name,
           ar.product_price,
           ar.product_address,
           ar.product_description,
           ar.product_image,
           GROUP_CONCAT(f.id) AS facilityIds
        FROM Available_Rooms ar
        LEFT JOIN Product_Facilities pf ON pf.product_id = ar.product_id
        LEFT JOIN Facilities f ON f.id = pf.facility_id
        GROUP BY 
            ar.product_id, 
            ar.product_category, 
            ar.product_name, 
            ar.product_price, 
            ar.product_address, 
            ar.product_description, 
            ar.product_image;
""", nativeQuery = true)
    List<Object[]> findAllProductsByCityAndDate(@Param("cityName") String cityName,
                                                @Param("person") int person,
                                                @Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);
    // 상세 조회
    @Query(value = """
        WITH Available_Rooms AS (
            SELECT
                p.id AS product_id,
                p.product_category,
                p.product_name,
                p.product_price,
                p.product_address,
                p.product_description,
                pi.product_image,
                sp.id AS sub_product_id,
                sp.sub_name,
                sp.sub_price,
                sp.sub_person,
                spi.sub_product_image
            FROM Products p
            INNER JOIN Product_images pi ON pi.product_id = p.id
            INNER JOIN Sub_Products sp ON sp.main_product_id = p.id
            INNER JOIN sub_product_images spi ON spi.sub_product_id = sp.id
            WHERE p.id = :productId
        )
        SELECT
            ar.product_id,
            ar.product_category,
            ar.product_name,
            ar.product_price,
            ar.product_address,
            ar.product_description,
            ar.product_image,
            ar.sub_product_id,
            ar.sub_name,
            ar.sub_price,
            ar.sub_person,
            ar.sub_product_image,
            f.id as facility_id,
            f.facility_name
        FROM Available_Rooms ar
        LEFT JOIN Product_Facilities pf ON pf.product_id = ar.product_id
        LEFT JOIN Facilities f ON f.id = pf.facility_id;
""", nativeQuery = true)
    List<Object[]> findProductDetailById(@Param("productId") Long productId);

    @Query(value = "SELECT p.id AS product_id, " +
            "p.product_name AS product_name, " +
            "accommodation.accommodation_name AS accommodation_name, " +
            "city.city_name AS city_name, " +
            "p.product_address AS product_address, " +
            "p.product_price AS product_price, " +
            "pi.product_image AS product_image " +
            "FROM Wish_List wl " +
            "JOIN Products p ON wl.product_id = p.id " +
            "JOIN Product_Accommodation_Categories pac ON pac.product_id = p.id " +
            "JOIN Accommodation_Categories accommodation ON pac.accommodation_id = accommodation.id " +
            "JOIN Product_Cities pc ON pc.product_id = p.id " +
            "JOIN cities city ON pc.city_id = city.id " +
            "JOIN Product_Images pi ON pi.product_id = p.id " +
            "GROUP BY p.id, p.product_name, accommodation.accommodation_name, city.city_name, p.product_address, p.product_price, pi.product_image " +
            "ORDER BY COUNT(wl.product_id) DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTop5Products();
}