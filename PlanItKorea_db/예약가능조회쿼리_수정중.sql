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
    spi.sub_product_image,
    r.id,
    r.start_date,
    r.end_date
from Products p
inner join Product_images pi on pi.product_id = p.id
inner join Product_Cities pc on pc.product_id = p.id
inner join cities c on c.id = pc.city_id
inner join Sub_Products sp on sp.main_product_id = p.id
inner join sub_product_images spi on spi.sub_product_id = sp.id
inner join Reservations r on r.sub_product_id = sp.id
where c.id = 1
AND sp.sub_person >= 2
AND (
    r.id IS NULL OR
NOT (r.start_date <= '2025-01-17 12:00:00.000000' AND r.end_date >= '2025-01-18 12:00:00.000000')
);