-- 전체 리스트 조회
WITH Available_Rooms AS (
    SELECT
        p.id AS product_id,
        p.product_category,
        p.product_name,
        p.product_price,
        p.product_address,
        pi.product_image,
        sp.id AS sub_product_id,
        sp.sub_name,
        sp.sub_price,
        sp.sub_person,
        spi.sub_product_image
    FROM Products p
    INNER JOIN Product_images pi ON pi.product_id = p.id
    INNER JOIN Product_Cities pc ON pc.product_id = p.id
    INNER JOIN Cities c ON c.id = pc.city_id
    INNER JOIN Sub_Products sp ON sp.main_product_id = p.id
    INNER JOIN sub_product_images spi ON spi.sub_product_id = sp.id
    WHERE c.city_name = "서울"
    AND sp.sub_person >= 2
    AND NOT EXISTS (
        SELECT 1
        FROM Reservations r
        WHERE r.sub_product_id = sp.id
        AND r.start_date <= '2025-01-17'
        AND r.end_date >= '2025-01-15'
    )
)
SELECT 
    ar.product_id,
    ar.product_category,
    ar.product_name,
    ar.product_price,
    ar.product_address,
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

-- 특정 ID 상세 조회
WITH Available_Rooms AS (
    SELECT
        p.id AS product_id,
        p.product_category,
        p.product_name,
        p.product_price,
        p.product_address,
        pi.product_image,
        sp.id AS sub_product_id,
        sp.sub_name,
        sp.sub_price,
        sp.sub_person,
        spi.sub_product_image
    FROM Products p
    INNER JOIN Product_images pi ON pi.product_id = p.id
    INNER JOIN Product_Cities pc ON pc.product_id = p.id
    INNER JOIN Cities c ON c.id = pc.city_id
    INNER JOIN Sub_Products sp ON sp.main_product_id = p.id
    INNER JOIN sub_product_images spi ON spi.sub_product_id = sp.id
    WHERE c.city_name = "서울"
    AND sp.sub_person >= 2
    AND NOT EXISTS (
        SELECT 1
        FROM Reservations r
        WHERE r.sub_product_id = sp.id
        AND r.start_date <= '2025-01-17'
        AND r.end_date >= '2025-01-15'
    )
)
SELECT 
    ar.product_id,
    ar.product_category,
    ar.product_name,
    ar.product_price,
    ar.product_address,
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