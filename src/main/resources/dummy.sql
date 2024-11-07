-- 더미 데이터 삽입 스크립트

-- Bank 데이터 삽입
-- bank 테이블에 데이터 삽입
INSERT INTO bank (code, name) VALUES
                                  ('001', '한국은행'),
                                  ('002', '산업은행'),
                                  ('003', '가상계좌 채번가능 기업은행'),
                                  ('004', '가상계좌 채번가능 국민은행'),
                                  ('005', '외환은행'),
                                  ('007', '수협은행'),
                                  ('008', '수출입은행'),
                                  ('011', '가상계좌 채번가능 농협은행'),
                                  ('012', '농협회원조합'),
                                  ('020', '가상계좌 채번가능 우리은행'),
                                  ('023', '가상계좌 채번가능 SC제일은행'),
                                  ('026', '서울은행'),
                                  ('027', '한국씨티은행'),
                                  ('031', '가상계좌 채번가능 대구은행'),
                                  ('032', '가상계좌 채번가능 부산은행'),
                                  ('034', '가상계좌 채번가능 광주은행'),
                                  ('035', '제주은행'),
                                  ('037', '전북은행'),
                                  ('039', '경남은행'),
                                  ('045', '새마을금고연합회'),
                                  ('048', '신협중앙회'),
                                  ('050', '상호저축은행'),
                                  ('051', '기타 외국계은행'),
                                  ('052', '모건스탠리은행'),
                                  ('054', 'HSBC은행'),
                                  ('055', '도이치은행'),
                                  ('056', '알비에스피엘씨은행'),
                                  ('057', '제이피모간체이스은행'),
                                  ('058', '미즈호코퍼레이트은행'),
                                  ('059', '미쓰비시도쿄UFJ은행'),
                                  ('060', 'BOA'),
                                  ('061', '비엔피파리바은행'),
                                  ('062', '중국공상은행'),
                                  ('063', '중국은행'),
                                  ('064', '산림조합'),
                                  ('065', '대화은행'),
                                  ('071', '가상계좌 채번가능 우체국'),
                                  ('076', '신용보증기금'),
                                  ('077', '기술신용보증기금'),
                                  ('081', '가상계좌 채번가능 하나은행'),
                                  ('088', '가상계좌 채번가능 신한은행'),
                                  ('089', '가상계좌 채번가능 케이뱅크'),
                                  ('090', '카카오뱅크'),
                                  ('092', '토스뱅크'),
                                  ('093', '한국주택금융공사'),
                                  ('094', '서울보증보험'),
                                  ('095', '경찰청'),
                                  ('099', '금융결제원'),
                                  ('209', '동양종합금융증권'),
                                  ('218', '현대증권'),
                                  ('230', '미래에셋증권'),
                                  ('238', '대우증권'),
                                  ('240', '삼성증권'),
                                  ('243', '한국투자증권'),
                                  ('247', 'NH투자증권'),
                                  ('261', '교보증권'),
                                  ('262', '하이투자증권'),
                                  ('263', '에이치엠씨투자증권'),
                                  ('264', '키움증권'),
                                  ('265', '이트레이드증권'),
                                  ('266', 'SK증권'),
                                  ('267', '대신증권'),
                                  ('268', '솔로몬투자증권'),
                                  ('269', '한화증권'),
                                  ('270', '하나대투증권'),
                                  ('278', '신한금융투자'),
                                  ('279', '동부증권'),
                                  ('280', '유진투자증권'),
                                  ('287', '메리츠증권'),
                                  ('289', '엔에이치투자증권'),
                                  ('290', '부국증권'),
                                  ('291', '신영증권'),
                                  ('292', '엘아이지투자증권');

-- Customer 데이터 삽입
INSERT INTO bank_member (id, email, name, jumin_number, gender, phone, address, birth, identity)
VALUES
    (1, 'customer1@example.com', '홍길동', '9001011234567', 'M', '010-1234-5678', '서울시 강남구', '1990-01-01', 'ID001'),
    (2, 'customer2@example.com', '김영희', '9202022345678', 'F', '010-2345-6789', '서울시 서초구', '1992-02-02', 'ID002');

-- Account 데이터 삽입
INSERT INTO core_account (id, customer_id, bank_code_id, account_number, balance, currency, account_type, fintech_use_num)
VALUES
    (1, 1, 1, '110-1234-5678', 1000000, 'KRW', 'PERSONAL', 'FNUM001'),
    (2, 2, 2, '120-2345-6789', 2000000, 'KRW', 'PERSONAL', 'FNUM002');

-- Card 데이터 삽입
INSERT INTO core_card (id, customer_id, account_id, card_name, card_number, cvc, is_issued, expired_at, card_status)
VALUES
    (1, 1, 1, '하나카드', '4862-1234-5678-9012', '123', TRUE, '2025-12', TRUE),
    (2, 2, 2, '국민카드', '4862-2345-6789-0123', '456', TRUE, '2026-06', TRUE);

-- Product 데이터 삽입
INSERT INTO bank_product (id, bank_id, name, rate)
VALUES
    (1, 1, '카카오 모임통장 상품', 1.5),
    (3, 2, '우리 일반통장 상품', 1.2);

-- Subscribe 데이터 삽입
INSERT INTO subscribe (id, bank_id, product_name, business_num, company_name, expire_date, is_subscribe, fee_amount, access_key, secret_key)
VALUES
    (1, 1, 'API 구독 상품', '123-45-67890', 'ABC 회사', '2025-11-07 00:00:00', TRUE, 100000, 'ACCESS_KEY_ABC', 'SECRET_KEY_HASH_ABC'),
    (2, 2, 'API 구독 상품', '234-56-78901', 'XYZ 회사', '2025-11-07 00:00:00', TRUE, 100000, 'ACCESS_KEY_XYZ', 'SECRET_KEY_HASH_XYZ');

-- History 데이터 삽입
INSERT INTO core_history (id, core_account_id, core_card, tran_type, tran_amt, after_balance_amt, withdraw_name, withdraw_account_num, description)
VALUES
    (1, 1, 1, 'DEPOSIT', 500000, 1500000, NULL, NULL, '월급 입금'),
    (2, 2, 2, 'WITHDRAW', 100000, 1900000, '편의점', '120-2345-6789', '편의점 결제');

-- CoreTransaction 데이터 삽입
INSERT INTO transaction (id, account_history_id, status)
VALUES
    (1, 1, 'SUCCESS'),
    (2, 2, 'SUCCESS');
