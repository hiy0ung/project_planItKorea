select 
	p.id,
    p.product_name,
    p.product_price,
    p.product_address,
    p.product_description,
    p.product_category, 
    pi.product_image,
    c.city_name,
    f.facility_name,
    sp.sub_name,
    sp.sub_person,
    sp.sub_price,
    sp.sub_description,
    spi.sub_product_image
from products p
left join product_images pi on pi.product_id = p.id
left join product_cities pc on pc.product_id = p.id
left join cities c on c.id = pc.city_id
left join product_facilities pf on pf.product_id = p.id
left join facilities f on f.id = pf.facility_id
left join sub_products sp on sp.main_product_id = p.id
left join sub_product_images spi on spi.sub_product_id = sp.id
where c.city_name = '서울'