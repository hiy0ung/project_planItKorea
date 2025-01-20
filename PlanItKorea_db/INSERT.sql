# 필요한 거 골라 쓰기!
INSERT INTO Products  VALUES
(default, '호텔 아즈르', '120,000 KRW', '서울 블루 레인 123번지', '도시의 스카이라인을 멋지게 볼 수 있는 고급 호텔입니다.'),
(default, '그린 에이커스 리조트', '200,000 KRW', '부산 포레스트 로드 456번지', '자연에 둘러싸인 고요한 리조트로, 평화로운 휴식을 제공합니다.'),
(default, '선셋 비치 인', '150,000 KRW', '제주 오션 드라이브 789번지', '해변에 바로 위치한 매력적인 인으로, 편안한 휴가에 적합합니다.'),
(default, '마운틴 리트리트', '180,000 KRW', '대구 피크 애비뉴 321번지', '등산과 자연을 사랑하는 사람들에게 이상적인 아늑한 산악 휴양지입니다.'),
(default, '시티 센터 로지', '100,000 KRW', '인천 어반 스트리트 654번지', '주요 명소와 가까운 도심 한가운데 위치한 편리한 숙소입니다.');

INSERT INTO Sub_Products VALUES
(default, 1, '디럭스 룸', '넓고 편안한 공간과 멋진 도시 전망을 자랑하는 디럭스 룸입니다.', '150,000 KRW', 2),
(default, 1, '스위트 룸', '럭셔리하고 세련된 인테리어와 함께 더욱 넓은 공간을 제공하는 스위트 룸입니다.', '250,000 KRW', 4),
(default, 2, '스탠다드 룸', '기본적인 편의시설과 함께 자연 경관을 즐길 수 있는 스탠다드 룸입니다.', '100,000 KRW', 2),
(default, 2, '패밀리 룸', '가족 여행에 적합한 넓고 편리한 패밀리 룸입니다.', '300,000 KRW', 4),
(default, 3, '더블 룸', '해변을 바라보며 편안한 휴식을 취할 수 있는 더블 룸입니다.', '180,000 KRW', 2),
(default, 3, '트윈 룸', '바다 전망과 함께 두 개의 싱글 침대를 제공하는 트윈 룸입니다.', '160,000 KRW', 2),
(default, 4, '싱글 룸', '혼자 여행하는 분들에게 적합한 아늑한 싱글 룸입니다.', '120,000 KRW', 1),
(default, 4, '더블 룸', '아름다운 산 전망과 함께 편안한 더블 룸입니다.', '200,000 KRW', 2),
(default, 5, '퀸 룸', '도심 한가운데서 편리함을 제공하는 넓고 쾌적한 퀸 룸입니다.', '130,000 KRW', 2),
(default, 5, '트윈 룸', '주요 명소와 가까운 편리한 위치의 트윈 룸입니다.', '110,000 KRW', 2);

insert into wish_list values
(default, 1, 1),
(default, 1, 2),
(default, 2, 1),
(default, 2, 3),
(default, 2, 5),
(default, 1, 4);

INSERT INTO cities VALUES 
(DEFAULT, "서울"),
(DEFAULT, "부산"),
(DEFAULT, "제주"),
(DEFAULT, "경주"),
(DEFAULT, "가평"),
(DEFAULT, "강릉"),
(DEFAULT, "여수"),
(DEFAULT, "전주"),
(DEFAULT, "해남"),
(DEFAULT, "대구");

INSERT INTO Accommodation_Categories VALUES 
(DEFAULT, "호텔&리조트"),
(DEFAULT, "펜션&풀빌라"),
(DEFAULT, "캠핑&글램핑");

# INSERT INTO Accommodation_Categories VALUES 
# (DEFAULT, "호텔"), 
# (DEFAULT, "리조트"),
# (DEFAULT, "펜션"),
# (DEFAULT, "풀빌라"),
# (DEFAULT, "캠핑"),
# (DEFAULT, "글램핑");

INSERT INTO Facilities VALUES 
(DEFAULT, "사우나"),
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

insert into Boards values 
(default, "공지사항", "test1", "공지사항테스트1", "이이이", now()),
(default, "자주묻는질문", "test2", "자주묻는질문테스트1", "김김김", now()),
(default, "공지사항", "test2", "공지사항테스트2", "박박박", now()),
(default, "자주묻는질문", "test2", "자주묻는질문테스트2", "최최최", now()),
(default, "공지사항", "test3", "공지사항테스트3", "이이이", now()),
(default, "자주묻는질문", "test3", "자주묻는질문테스트3", "김김김", now()),
(default, "공지사항", "test4", "공지사항테스트4", "박박박", now()),
(default, "자주묻는질문", "test4", "자주묻는질문테스트4", "최최최", now());