CREATE DATABASE exam_plan_it_korea;
USE exam_plan_it_korea;

# 유저 테이블 
CREATE TABLE Users (
	id BIGINT AUTO_INCREMENT PRIMARY KEY, # 고유 값
    user_id VARCHAR(255) NOT NULL UNIQUE, # 아이디
    user_password VARCHAR(255) NOT NULL, # 비밀번호
    user_name VARCHAR(255) NOT NULL, # 이름
    user_birth_date DATE NOT NULL, # 생년월일
    user_phone VARCHAR(15) NOT NULL UNIQUE, # 전화번호
    user_email VARCHAR(255) NOT NULL UNIQUE, # 이메일
    sns_id VARCHAR(255) DEFAULT NULL, # sns 로그인 아이디
    join_path VARCHAR(5) NOT NULL # 회원가입 경로
);

# 숙소 상품 테이블 
CREATE TABLE Products (
	id BIGINT AUTO_INCREMENT PRIMARY KEY, # 고유 값
    product_name VARCHAR(255) NOT NULL, # 숙소 이름
    product_price VARCHAR(255) NOT NULL, # 숙소 평균 가격? 객실과 별도로 최상위 홈에 띄어지는
    product_address VARCHAR(255) NOT NULL, # 숙소 주소
    product_description TEXT NOT NULL # 숙소 설명
);

# 숙소 설명 테이블 생성 ?


# 예약 정보 테이블 
CREATE TABLE Reservations (
	user_id BIGINT NOT NULL, # 유저 고유 값 
    product_id BIGINT NOT NULL, # 숙소 고유 값
    person INT NOT NULL, # 인원 수 
    start_date DATE NOT NULL, # 체크인
    end_date DATE NOT NULL, # 체크아웃
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Products(id) ON DELETE CASCADE
);

# 위시 리스트 정보 테이블 
CREATE TABLE Wish_List (
	user_id BIGINT NOT NULL, # 유저 고유 값
    product_id BIGINT NOT NULL, # 숙소 고유 값
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Products(id) ON DELETE CASCADE
);

# 숙소 지역  
CREATE TABLE cities (
	id BIGINT AUTO_INCREMENT PRIMARY KEY, # 고유 값
    city_name VARCHAR(255) NOT NULL # 지역 이름 (서울, 부산, 제주, 경주, 가평, 강릉, 여수, 전주, 해남, 대구 등)
);

INSERT INTO cities 
VALUES (DEFAULT, "서울"),
		(DEFAULT, "부산"),
        (DEFAULT, "제주"),
        (DEFAULT, "경주"),
        (DEFAULT, "가평"),
        (DEFAULT, "강릉"),
        (DEFAULT, "여수"),
        (DEFAULT, "전주"),
        (DEFAULT, "해남"),
        (DEFAULT, "대구");
        

# 상품 & 지역 연결 테이블
CREATE TABLE Product_Cities (
	id BIGINT AUTO_INCREMENT PRIMARY KEY, # 고유 값
	product_id BIGINT NOT NULL, # 숙소 고유 값
    city_id BIGINT NOT NULL, # 지역 고유 값
    FOREIGN KEY (product_id) REFERENCES Products(id) ON DELETE CASCADE,
    FOREIGN KEY (city_id) REFERENCES cities(id)
);

# 숙소 유형
CREATE TABLE Accommodation_Categories (
	id BIGINT AUTO_INCREMENT PRIMARY KEY, # 고유 값
    accommodation_name VARCHAR(255) NOT NULL # 숙소 유형 (호텔&리조트, 펜션&풀빌라, 캠핑&글램핑 등 ?)
);

INSERT INTO Accommodation_Categories
VALUES (DEFAULT, "호텔&리조트"),
		(DEFAULT, "펜션&풀빌라"),
		(DEFAULT, "캠핑&글램핑");
        
INSERT INTO Accommodation_Categories
VALUES (DEFAULT, "호텔"),
		(DEFAULT, "리조트"),
		(DEFAULT, "펜션"),
		(DEFAULT, "풀빌라"),
		(DEFAULT, "캠핑"),
		(DEFAULT, "글램핑");

# 상품 & 숙소 유형 연결 테이블
CREATE TABLE Product_Accommodation_Categories (
	id BIGINT AUTO_INCREMENT PRIMARY KEY, # 고유 값
    product_id BIGINT NOT NULL, # 숙소 고유 값
    accommodation_id BIGINT NOT NULL, # 숙소 유형 고유 값
    FOREIGN KEY (product_id) REFERENCES Products(id),
    FOREIGN KEY (accommodation_id) REFERENCES Accommodation_Categories(id)
);

# 편의 시설
CREATE TABLE Facilities (
	id BIGINT AUTO_INCREMENT PRIMARY KEY, # 고유 값
    facility_name VARCHAR(255) NOT NULL # 편의 시설 (사우나, 수영장, 바베큐, 세탁, 스파/월풀, 와이파이, 에어컨, 샤워실, 욕실용품, 조식, 주차, 반려견, 취사, OTT 등)
);

INSERT INTO Facilities
VALUES (DEFAULT, "사우나"),
		(DEFAULT, "수영장"),
		(DEFAULT, "바베큐"),
		(DEFAULT, "세탁"),
		(DEFAULT, "스파&월풀"),
		(DEFAULT, "와이파이"),
		(DEFAULT, "에어컨"),
		(DEFAULT, "샤워실"),
		(DEFAULT, "욕실용품"),
		(DEFAULT, "조식"),
		(DEFAULT, "주차"),
		(DEFAULT, "반려견"),
		(DEFAULT, "취사"),
		(DEFAULT, "OTT");

# 숙소 & 편의 시설 연결 테이블
CREATE TABLE Product_Facilities (
	id BIGINT AUTO_INCREMENT PRIMARY KEY, # 고유 값
    product_id BIGINT NOT NULL, # 상품 고유 값
    facility_id BIGINT NOT NULL, # 편의 시설 고유 값
	FOREIGN KEY (product_id) REFERENCES Products(id),
    FOREIGN KEY (facility_id) REFERENCES Facilities(id)
);

# 메인 상품 이미지
CREATE TABLE Product_Images (
	product_id BIGINT NOT NULL, # 숙소 고유 값
    product_image VARCHAR(255) NOT NULL, # 숙소 이미지
    FOREIGN KEY (product_id) REFERENCES Products(id) ON DELETE CASCADE
);

# 서브 상품 객실
CREATE TABLE Sub_Products (
	id BIGINT AUTO_INCREMENT PRIMARY KEY, # 고유 값
    main_product_id BIGINT NOT NULL, # 숙소 고유 값
    sub_name VARCHAR(255) NOT NULL, # 객실 이름
    sub_description TEXT NOT NULL, # 객실 설명 
    sub_price VARCHAR(255) NOT NULL, # 객실 가격
    sub_person INT NOT NULL, # 객실 정원 수
    FOREIGN KEY (main_product_id) REFERENCES Products(id) ON DELETE CASCADE
);

# 객실 예약 날짜
CREATE TABLE Sub_Products_Date (
	id BIGINT AUTO_INCREMENT PRIMARY KEY, # 고유 값
    sub_product_id BIGINT NOT NULL, # 객실 고유 값
    start_date DATE NOT NULL, # 객실 체크인
    end_date DATE NOT NULL, # 객실 체크아웃
    FOREIGN KEY (main_product_id) REFERENCES Sub_Products(id) ON DELETE CASCADE
);

# 서브 상품 이미지
CREATE TABLE Sub_Product_Images (
	sub_product_id BIGINT NOT NULL, # 고유 값
    sub_product_image VARCHAR(255) NOT NULL, # 객실 이미지
    FOREIGN KEY (product_id) REFERENCES Sub_Products(id) ON DELETE CASCADE
);

# 공지 사항
CREATE TABLE Boards (
	id BIGINT AUTO_INCREMENT PRIMARY KEY, # 고유 값
    board_type ENUM("공지사항", "자주묻는질문"), # 게시물 유형
    board_title VARCHAR(255) NOT NULL, # 게시물 제목
    board_content TEXT NOT NULL, # 게시물 내용
    author VARCHAR(255) NOT NULL, # 작성자
    upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL # 업로드 시간
);

# 1대1 질문 게시판
CREATE TABLE Inquiries (
	id BIGINT AUTO_INCREMENT PRIMARY KEY, # 고유 값
    user_id BIGINT, # 유저 고유 값
    inquiry_title VARCHAR(255) NOT NULl, # 질문 제목
    inquiry_categorys ENUM("결제", "취소", "환불"), # 질문 유형
    inquiry_content TEXT NOT NULL, # 질문 내용
    inquiry_image VARCHAR(255)  # 질문 이미지
);

# 리뷰 게시판
CREATE TABLE Reviews (
	id BIGINT AUTO_INCREMENT PRIMARY KEY, # 고유 값
    product_id BIGINT NOT NULL, # 숙소 고유 값
    user_id BIGINT NOT NULL, # 유저 고유 값
    review_commend TEXT NOT NULL, # 리뷰 내용
    review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, # 리뷰 업로드 날짜
    FOREIGN KEY (product_id) REFERENCES Products(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);