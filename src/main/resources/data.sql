-- 더미 데이터 삽입 스크립트

-- Bank 데이터 삽입
-- bank 테이블에 데이터 삽입
INSERT INTO bank (id, code, name, bank_image)
VALUES (1, '001', '통합은행', 'https://djogyo1sj025q.cloudfront.net/banklogo/001.png'),
       (4, '004', '가상계좌 채번가능 국민은행', 'https://djogyo1sj025q.cloudfront.net/banklogo/004.png'),
       (10, '020', '가상계좌 채번가능 우리은행', 'https://djogyo1sj025q.cloudfront.net/banklogo/020.png'),
       (40, '081', '가상계좌 채번가능 하나은행', 'https://djogyo1sj025q.cloudfront.net/banklogo/081.png'),
       (43, '090', '카카오뱅크', 'https://djogyo1sj025q.cloudfront.net/banklogo/090.png'),
       (44, '092', '토스뱅크', 'https://djogyo1sj025q.cloudfront.net/banklogo/092.png'),
       (41, '088', '가상계좌 채번가능 신한은행', 'https://djogyo1sj025q.cloudfront.net/banklogo/088.png');
INSERT INTO bank (id, code, name)
VALUES (2, '002', '산업은행'),
       (3, '003', '가상계좌 채번가능 기업은행'),
       (5, '005', '외환은행'),
       (6, '007', '수협은행'),
       (7, '008', '수출입은행'),
       (8, '011', '가상계좌 채번가능 농협은행'),
       (9, '012', '농협회원조합'),
       (11, '023', '가상계좌 채번가능 SC제일은행'),
       (12, '026', '서울은행'),
       (13, '027', '한국씨티은행'),
       (14, '031', '가상계좌 채번가능 대구은행'),
       (15, '032', '가상계좌 채번가능 부산은행'),
       (16, '034', '가상계좌 채번가능 광주은행'),
       (17, '035', '제주은행'),
       (18, '037', '전북은행'),
       (19, '039', '경남은행'),
       (20, '045', '새마을금고연합회'),
       (21, '048', '신협중앙회'),
       (22, '050', '상호저축은행'),
       (23, '051', '기타 외국계은행'),
       (24, '052', '모건스탠리은행'),
       (25, '054', 'HSBC은행'),
       (26, '055', '도이치은행'),
       (27, '056', '알비에스피엘씨은행'),
       (28, '057', '제이피모간체이스은행'),
       (29, '058', '미즈호코퍼레이트은행'),
       (30, '059', '미쓰비시도쿄UFJ은행'),
       (31, '060', 'BOA'),
       (32, '061', '비엔피파리바은행'),
       (33, '062', '중국공상은행'),
       (34, '063', '중국은행'),
       (35, '064', '산림조합'),
       (36, '065', '대화은행'),
       (37, '071', '가상계좌 채번가능 우체국'),
       (38, '076', '신용보증기금'),
       (39, '077', '기술신용보증기금'),
       (42, '089', '가상계좌 채번가능 케이뱅크'),
       (45, '093', '한국주택금융공사'),
       (46, '094', '서울보증보험'),
       (47, '095', '경찰청'),
       (48, '099', '금융결제원'),
       (49, '209', '동양종합금융증권'),
       (50, '218', '현대증권'),
       (51, '230', '미래에셋증권'),
       (52, '238', '대우증권'),
       (53, '240', '삼성증권'),
       (54, '243', '한국투자증권'),
       (55, '247', 'NH투자증권'),
       (56, '261', '교보증권'),
       (57, '262', '하이투자증권'),
       (58, '263', '에이치엠씨투자증권'),
       (59, '264', '키움증권'),
       (60, '265', '이트레이드증권'),
       (61, '266', 'SK증권'),
       (62, '267', '대신증권'),
       (63, '268', '솔로몬투자증권'),
       (64, '269', '한화증권'),
       (65, '270', '하나대투증권'),
       (66, '278', '신한금융투자'),
       (67, '279', '동부증권'),
       (68, '280', '유진투자증권'),
       (69, '287', '메리츠증권'),
       (70, '289', '엔에이치투자증권'),
       (71, '290', '부국증권'),
       (72, '291', '신영증권'),
       (73, '292', '엘아이지투자증권');


-- Customer 데이터 삽입
INSERT INTO bank_member (email, name, phone_num, ci)
VALUES ('customer21@example.com', '크루즈', '01012345678',
        'StWtvgc2xY6IhtUhkL/cFL8d+1FxSRs5bfXcxIRtoFV+e/Tp30gy+oXeGXhUjb846qboNbs/go+SwRrNDsAXiw=='),
       ('banks@bank.com', '은행', '01060600000',
        'S+/u3+XbKcqLaGfB8zi69veu7fzjoAt4C4sWAXyM+u6zgM5fl4J7nuAQ/5FGrEhXN85np4U+L8fXpMmYfQQjfQ=='),
       ('leemyungruyl@fisa3rd.com', '이명렬', '01060262912',
        'wwEuaxGt1pbJgd9eo+eclGZIeZfDcI36vrklp4XJH6deJ6A7vPFG+KjVrNk/zGUD8kTVfYp2TPlTrfH2TwkLvA=='),
       ('leedoee@fisa3rd.com', '이도이', '01012145618',
        'OV2QPF2l/1oT2dH+I2VXe+4GCDVHciBI3IlGgRl6Hy8S+QJYoOXAcvgqV1ByM+ikIke30/9Chwr3ITmMKVy8uA=='),
       ('gongyejin@fisa3rd.com', '공예진', '01024608434',
        '3caSW2rtGumeLkjcqFaaOipyS+KlIhjA1CgVmPqQb1kGtxAVvyGS4IiIqH5WZ9LQCtlAZvgQlPh5NuItBE6FeQ=='),
       ('kimhyebin@fisa3rd.com', '김혜빈', '01038539931',
        'TXnvBOgPt8mxqYVD2PJol5pXtiPkXiNHdN2SWERdBZdjsZalyoxNWWtuHpm7989eMqeZogvM8O+WRNRWX7/3+g=='),
       ('kangjaeyeon@fisa3rd.com', '강재연', '01024535350',
        'RdmTfd2DQfmzWtKtk+jgRo3DTkvfqqcfWbTLMJ+25LxkoHU5v8KfWpGYu/oWjrIJDEE3+TwpiBIQbJRDIuLq5g=='),
       ('kwakjieun@fisa3rd.com', '곽지은', '01062519696',
        '2DZwWQW4mbnhYmtMY0lE4W8Ak0jI3rCQhw31SwKhVdynQXBOMufkQZdmGqA6MIvuikOdhfMRhNEyROijeLPWvg=='),
       ('kimchangyoung@fisa3rd.com', '김창영', '01092098699',
        'zGsNDHbkqL51Ov+yW6fvQGVqqLLDGri50f5K7aAwwD2yHdIeSk/ZzganHCRNj+ziY1eSVyEx6vrNonZGPVQqgA=='),
       ('yuseunga@fisa3rd.com', '유승아', '01032578903',
        'grBhUBs3OO9WwYmrEJlvywY1VmgAW/XGktU5UQYlllr5G/jpqpFCZZ+LsT2PLMNEBz7N5Y5WUGglzXAamS/9sw=='),
       ('hongchanye@fisa3rd.com', '홍찬의', '01061573613',
        '1d7cuznL6Jc9t5bJBORNme+ezHAV7HkoKHWJc03j2PnUzPXKZgJQIUXqyOcCoBxz9XqjXmvX5kdJjiymDUvJsg=='),
       ('oseonmin@fisa3rd.com', '오선민', '01080036612',
        '0xZiUK3wzLruPgyWmDyFJopZB5MjzEk7zCkih8hraBLM/7suSoWeYirdyp59+Fi97DpWOWgsO0ouhjbMh2Hqxw=='),
       ('jungseokjin@fisa3rd.com', '정석진', '01097684636',
        'nV54xSVQjsILPA2PySKovbifoPr2jy4aMCW++XJVth4pGgWBdX1JZRVK13ZAyWwCpkntKNHYE0sMoFwsFfynAg=='),
       ('kimseoyeon@fisa3rd.com', '김서연', '01084941863',
        'KJY018/0Mur5C2WTxxxAev4qGyNY/w7CMFiJr66BOOeD0qbxZmywrmID/dGhgB2h823QI96/vZ+5xAZjnUNyvw=='),
       ('anchanwoong@fisa3rd.com', '안찬웅', '01071779411',
        'NGmTrSBZYj3crZTfW5U1RNq2UwwPg8+FC6TFQd1ZHeppDcfE3cTk5szUQklueFafNNiD6XbJ/mbIfuTYN0bSLw=='),
       ('bangseonggyung@fisa3rd.com', '방성경', '01029419427',
        'bWCfsZUOiVNwlVyRn6lOFibMjvjlNj4NNj7EGXabcz2EpLBGHbjtMLjAHbtbyyOllmO0AgZL5HGRBxh9PpBRTQ=='),
       ('kimhyunwoo@fisa3rd.com', '김현우', '01028266991',
        'a57pahzEf8J1ELR5x6F53N/+lm+2yqCunyPu9LWUEULDAokUAJSWJL9BbzYajJoaw+SqZ8OBAcoZWXTzSRcJ9w=='),
       ('kimhocheol@fisa3rd.com', '김호철', '01065689080',
        'qdzLNvumBVDPe9t5CC/D6F6p+fMEHBWIMaQL+45kBmEJBOsB9Ww7uQ3Ifmls2TOHkw41dW4UqGimu5WPOTgaBQ=='),
       ('parkjunhyun@fisa3rd.com', '박준현', '01030346670',
        'FRCu9C/9x2/hji8ET84MoLrOg3T0QUTgUtFxjHKNlps5SPccXvgzwcNNd050D7RRXdIzYi6PPULm56DdqTw3rw=='),
       ('kilga-eun@fisa3rd.com', '길가은', '01031024245',
        'YyahroelsIq9ZLhdn/llzsdZ6RvpXrDgrES3rFDhkdd96qIW7Z0C1hej6gporiAyuArABB9ADvf5kSZiH0OTGQ=='),
       ('gongsoyeon@fisa3rd.com', '공소연', '01071239332',
        'heLuKtfwoCfQmoXuPtL2GFD6cq6cHlTVFt+10lv3xf+XKfjHyF5LjLZuD+L0wsCNIM0jx8dEJQ/w9jEq3Yi1yQ=='),
       ('kanghyunwoo@fisa3rd.com', '강현우', '01029292403',
        'Pn+HTBYdB3cmmZx4GyEUhYHeeoYMK81Ncb67F2/GgwC1tVu6YUAn90jttuqeZULnZ5KS3BAkUgoB/+V7cny8PQ==');

-- Product 데이터 삽입
INSERT INTO bank_product (id, bank_id, name, highest_rate, lowest_rate)
VALUES (1, 1, '카카오 모임통장 상품', 2.0, 1.5),
       (2, 2, '우리 일반통장 상품', 2.3, 1.2);

-- Account 데이터 삽입
INSERT
INTO core_account (id, customer_id, bank_code_id, product_id, account_number, balance, currency, account_type,
                   fintech_use_num, is_deleted)
VALUES (1, 2, 1, 1, '77777777777', 10000000000000,
        'KRW', 'PERSONAL', '9df5bf03-cf53-4a48-8c6d-03b8c36f5783', FALSE),
       (2, 5, 2, 1, '12023456789', 2000000,
        'KRW', 'PERSONAL', '9bf53d03-cff5-448a-8c8d-03b36f5c6784',
        FALSE),
       (3, 8, 1, 1, '11034567890', 200000,
        'KRW', 'PERSONAL', 'a7f850c0-8db4-4d12-8cc8-ea4a838fcf18',
        FALSE),
       (4, 3, 10, 1, '1002844028454', 10240000,
        'KRW', 'PERSONAL', '7a93e8ce-ccb3-47a1-b7f6-c30991363005',
        FALSE),
       (5, 1, 3, 1, '33078901234', 300000,
        'KRW', 'PERSONAL', '4f8bd5a5-57b4-4258-ba5b-de9581762bb3',
        FALSE),
       (6, 2, 44, 1, '09200010001',
        10000000, 'KRW', 'CORPORATE',
        'cbe1d1d6-c72e-43f9-b6d4-fe1bed13ba3f', FALSE),
       (7, 2, 43, 1, '09000010001',
        15000000, 'KRW', 'CORPORATE',
        '9a34e796-83c2-482c-9c88-a43e45c03547', FALSE),
       (8, 2, 41, 1, '08800010001',
        12000000, 'KRW', 'CORPORATE',
        'e7d0c434-82a7-4a3d-9d4c-0d8431515623', FALSE),
       (9, 2, 10, 1, '02000010001',
        13000000, 'KRW', 'CORPORATE',
        'e4f2c142-997a-4dbb-9b91-1f7003a0f219', FALSE),
       (11, 1, 10, 1, '1002844010203',
        30000000, 'KRW', 'CORPORATE',
        '9e9196e7-0469-4424-8c57-076e6f16a284', FALSE);

-- Card 데이터 삽입
INSERT INTO core_card (id, customer_id, account_id, card_name, card_number, cvc, is_issued, expired_at, card_status)
VALUES (1, 1, 1, '하나카드', '4862123456789012', '123', TRUE, '2025-11-07 00:00:00', TRUE),
       (2, 2, 2, '국민카드', '4862234567890123', '456', TRUE, '2025-11-07 00:00:00', TRUE);


-- Subscribe 데이터 삽입
INSERT INTO subscribe (id, bank_id, product_name, business_num, company_name, expire_date, is_subscribe, fee_amount,
                       access_key, secret_key)
VALUES (1, 1, 'API 구독 상품', '123-45-67890', '크루즈', '2025-11-07 00:00:00', TRUE, 100000,
        '7d336211-107f-476e-b910-6afd64503450',
        'vSSqvul8FLDhN2IIQgmn0dy79jOI4hWjRZXgtSAWw1A='),
       (2, 2, 'API 구독 상품', '234-56-78901', 'Crews', '2025-11-07 00:00:00', TRUE, 100000,
        '87d8ee92-6000-40bf-9f3f-f548a683061e',
        'KMq60SuylJxV6/zO7HY67FzQDTlQwjrBUnRJa8xCJMA=');

-- History 데이터 삽입
INSERT INTO core_history (id, core_account_id, core_card, tran_type, tran_amt, after_balance_amt, counterparty_name,
                          counterparty_account_num, counterparty_bank_code, description)
VALUES (1, 1, 1, 'DEPOSIT', 500000, 1500000, '김치도가', '11012345678', '020', '월급 입금'),
       (2, 2, 2, 'WITHDRAW', 100000, 1900000, '편의점', '12023456789', '020', '편의점 결제');

-- CoreTransaction 데이터 삽입
INSERT INTO transaction (id, status)
VALUES (1, 'SUCCESS'),
       (2, 'SUCCESS');
