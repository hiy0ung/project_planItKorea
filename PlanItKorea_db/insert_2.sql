SELECT * FROM Products;
SELECT * FROM Cities;
SELECT * FROM product_tb;
SELECT * FROM Product_Cities;
SELECT * FROM Facilities;
SELECT * FROM Product_Facilities;
SELECT * FROM Product_Images;
SELECT * FROM Sub_Products;

delete from Product_Cities;
alter table Product_Cities auto_increment=1;

INSERT INTO Products (product_name, product_price, product_address, product_description, product_category) 
VALUES 
('서울 호텔', '200000', '서울특별시 강남구', '럭셔리한 호텔입니다.', '호텔&리조트'),
('부산 리조트', '180000', '부산 해운대구', '바다가 보이는 리조트입니다.', '호텔&리조트'),
('제주 펜션', '150000', '제주 서귀포시', '조용한 휴식을 제공하는 펜션입니다.', '펜션&풀빌라'),
('가평 풀빌라', '220000', '경기도 가평군', '자연 속에서 힐링할 수 있는 풀빌라.', '펜션&풀빌라'),
('강릉 캠핑장', '100000', '강원도 강릉시', '자연과 함께하는 캠핑 경험.', '캠핑&글램핑'),
('여수 글램핑', '130000', '전라남도 여수시', '럭셔리한 글램핑 시설 제공.', '캠핑&글램핑');

INSERT INTO Product_Cities (product_id, city_id) 
VALUES 
(1, 1), -- 서울 호텔은 서울에 위치
(2, 1), -- 서울 호텔은 서울에 위치
(3, 1), -- 서울 호텔은 서울에 위치
(4, 2), -- 부산 리조트는 부산에 위치
(5, 3), -- 제주 펜션은 제주에 위치
(6, 5), -- 가평 풀빌라는 가평에 위치
(7, 6), -- 강릉 캠핑장은 강릉에 위치
(8, 7); -- 여수 글램핑은 여수에 위치

INSERT INTO Product_Facilities (product_id, facility_id) 
VALUES 
(3, 1), (3, 6), (3, 7), (3, 10), -- 서울 호텔: 사우나, 와이파이, 에어컨, 조식
(4, 2), (4, 5), (4, 6), (4, 11), -- 부산 리조트: 수영장, 스파/월풀, 와이파이, 주차
(5, 3), (5, 4), (5, 8), (5, 9), (5, 12), -- 제주 펜션: 바베큐, 세탁, 샤워실, 욕실용품, 반려견 허용
(6, 3), (6, 6), (6, 7), (6, 11), -- 가평 풀빌라: 바베큐, 와이파이, 에어컨, 주차
(7, 12), (7, 13), (7, 14), -- 강릉 캠핑장: 반려견 동반 가능, 취사 가능, OTT 지원
(8, 6), (8, 7), (8, 8), (8, 9); -- 여수 글램핑: 와이파이, 에어컨, 샤워실, 욕실용품

INSERT INTO Product_Images (product_id, product_image) 
VALUES 
(3, 'seoul_hotel_1.jpg'), (3, 'seoul_hotel_2.jpg'), (3, 'seoul_hotel_3.jpg'),
(4, 'busan_resort_1.jpg'), (4, 'busan_resort_2.jpg'),
(5, 'jeju_pension_1.jpg'), (5, 'jeju_pension_2.jpg'), (5, 'jeju_pension_3.jpg'),
(6, 'gapyeong_poolvilla_1.jpg'), (6, 'gapyeong_poolvilla_2.jpg'),
(7, 'gangneung_camping_1.jpg'), (7, 'gangneung_camping_2.jpg'),
(8, 'yeosu_glamping_1.jpg'), (8, 'yeosu_glamping_2.jpg');

INSERT INTO Sub_Products (main_product_id, sub_name, sub_description, sub_price, sub_person) 
VALUES 
(3, '디럭스 룸', '넓고 쾌적한 디럭스 룸.', '250000', 2),
(3, '프리미엄 스위트', '고급스러운 분위기의 스위트룸.', '350000', 3),
(4, '오션뷰 스위트', '바다 전망이 보이는 스위트룸.', '300000', 4),
(4, '스탠다드 룸', '기본적인 편의시설을 갖춘 룸.', '200000', 2),
(5, '가족형 펜션', '가족 단위 숙박에 적합한 공간.', '180000', 6),
(5, '커플룸', '커플에게 적합한 아늑한 객실.', '160000', 2),
(6, '스파 풀빌라', '프라이빗 스파가 포함된 풀빌라.', '280000', 4),
(6, '자쿠지 풀빌라', '개인 자쿠지가 포함된 풀빌라.', '320000', 2),
(7, '캠핑 사이트 A', '전기 및 물 공급이 포함된 캠핑 사이트.', '120000', 4),
(7, '캠핑 사이트 B', '자연 그대로의 감성을 즐길 수 있는 캠핑 사이트.', '100000', 2),
(8, '럭셔리 텐트', '에어컨과 편의시설을 갖춘 고급 텐트.', '150000', 4),
(8, '패밀리 텐트', '가족 단위 이용이 가능한 넓은 텐트.', '170000', 6);

# 서브 상품 이미지
CREATE TABLE Sub_Product_Images (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	sub_product_id BIGINT NOT NULL, # 고유 값
    sub_product_image VARCHAR(255) NOT NULL, # 객실 이미지
    FOREIGN KEY (sub_product_id) REFERENCES Sub_Products(id) ON DELETE CASCADE
);

insert into Sub_Product_Images (sub_product_id, sub_product_image) values
(4, "img_4.jpg"),
(5, "img_5.jpg"),
(6, "img_6.jpg"),
(7, "img_7.jpg"),
(8, "img_8.jpg"),
(9, "img_9.jpg"),
(10, "img_10.jpg"),
(11, "img_11.jpg"),
(12, "img_12.jpg"),
(13, "img_13.jpg"),
(14, "img_14.jpg"),
(15, "img_15.jpg");
