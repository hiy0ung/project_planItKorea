SELECT DISTINCT u.USER_ID
FROM `USERS` u
JOIN `AVAILABLE_DATE_OF_WEEK` adw ON u.USER_ID = adw.PROVIDER_ID
WHERE adw.AVAILABLE_DATE BETWEEN :startDate AND :endDate
# 예약 충돌 방지 -> 동일한 제공자에 대해 예약 시작일이 종료일 이전이거나, 예약 종료일이 시작일 이후인 경우 존재 X 해야함
AND NOT EXISTS (
    SELECT 1
    FROM `RESERVATIONS` r
    WHERE r.PROVIDER_ID = u.USER_ID
    AND r.RESERVATION_START_DATE <= :endDate
    AND r.RESERVATION_END_DATE >= :startDate
)
GROUP BY u.USER_ID
# 날짜 범위 내 모든 날짜가 예약 가능한지 확인
# DATE_ADD(:startDate, INTERVAL n DAY) AS required_date >> start-end까지 날짜 생성
# union all로 0~6까지 최대 7일의 날짜 범위 처리

HAVING NOT EXISTS (
    SELECT 1
    FROM (
        SELECT DATE_ADD(:startDate, INTERVAL n DAY) AS required_date
        FROM (SELECT 0 AS n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3
              UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6) days
        WHERE DATE_ADD(:startDate, INTERVAL n DAY) <= :endDate
    ) required_dates
    # not exists를 사용해 AVAILABLE_DATE_OF_WEEK가 required_dates와 일치하지 않는 경우가 있는지 확인
    WHERE NOT EXISTS (
        SELECT 1
        FROM AVAILABLE_DATE_OF_WEEK adw_inner
        WHERE adw_inner.PROVIDER_ID = u.USER_ID
        AND adw_inner.AVAILABLE_DATE = required_dates.required_date
    )
);

--  Set<Long> findProviderByDate(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);