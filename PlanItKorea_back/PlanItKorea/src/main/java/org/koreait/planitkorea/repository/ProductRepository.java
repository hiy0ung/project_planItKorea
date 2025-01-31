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
    @Query(value = """
        select
            p.id,
            p.product_name,
            p.product_price,
            p.product_address,
            pi.product_image,
            sp.id sub_product_id,
            sp.sub_name,
            sp.sub_price,
            sp.sub_person,
            spi.sub_product_image
        from Products p
        inner join Product_images pi on pi.product_id = p.id
        inner join Product_Cities pc on pc.product_id = p.id
        inner join cities c on c.id = pc.city_id
        inner join Sub_Products sp on sp.main_product_id = p.id
        inner join sub_product_images spi on spi.sub_product_id = sp.id
        LEFT JOIN Reservations r ON r.sub_product_id = sp.id
            AND NOT (
                r.start_date > :endDate
                OR r.end_date < :startDate
            )
        WHERE c.city_name = :cityName
        AND sp.sub_person >= :person
        AND r.id IS NULL;
""", nativeQuery = true)
    List<Object[]> findAllProductsByCityAndDate(@Param("cityName") String cityName,
                                                @Param("person") int person,
                                                @Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);
}
