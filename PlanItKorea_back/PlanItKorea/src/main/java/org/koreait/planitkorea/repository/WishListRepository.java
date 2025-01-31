package org.koreait.planitkorea.repository;

import org.koreait.planitkorea.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    Optional<WishList> findByUserIdAndId(Long userId, Long id);

    @Query(value = """
        select
            w.user_id,
            w.product_id,
            p.product_name,
            p.product_address,
            p.product_price,
            pi.product_image
        from wish_list w
        left outer join products p on p.id = w.product_id
        left outer join product_images pi on pi.product_id = p.id
        where w.user_id = :userId
""", nativeQuery = true)
    List<Object[]> getWishListByUserId(@Param("userId") Long userId);
}
