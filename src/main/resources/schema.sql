-- 데이터베이스 초기화 스크립트

-- 데이터베이스 삭제 및 생성
DROP DATABASE IF EXISTS baascore;
CREATE DATABASE IF NOT EXISTS baascore;

USE baascore;

-- Bank 테이블 생성
CREATE TABLE IF NOT EXISTS bank
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    code       VARCHAR(255) NOT NULL, -- 기존에 20자 제한이었으나, 일부 상황에서 부족할 수 있으므로 255자로 변경
    name       VARCHAR(255) NOT NULL,
    bank_image VARCHAR(255) DEFAULT '',
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Customer 테이블 생성
CREATE TABLE IF NOT EXISTS bank_member
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    email      VARCHAR(255) NOT NULL UNIQUE,
    name       VARCHAR(255) NOT NULL,
    phone_num  VARCHAR(255) NOT NULL UNIQUE,
    ci         VARCHAR(88),
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

);
-- Product 테이블 생성
CREATE TABLE IF NOT EXISTS bank_product
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    bank_id      BIGINT       NOT NULL,
    name         VARCHAR(255) NOT NULL,
    highest_rate DOUBLE       NOT NULL,
    lowest_rate  DOUBLE       NOT NULL,
    created_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (bank_id) REFERENCES bank (id)
);

-- Account 테이블 생성
CREATE TABLE IF NOT EXISTS core_account
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id     BIGINT         NOT NULL,
    bank_code_id    BIGINT         NOT NULL,
    product_id      BIGINT         NOT NULL,
    account_number  VARCHAR(14)    NOT NULL UNIQUE,
    balance         DECIMAL(38, 2) NOT NULL,
    currency        VARCHAR(10),
    account_type    VARCHAR(20),
    fintech_use_num VARCHAR(255)   NOT NULL UNIQUE,
    is_deleted      BOOLEAN        NOT NULL,
    created_at      DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES bank_member (id),
    FOREIGN KEY (bank_code_id) REFERENCES bank (id),
    FOREIGN KEY (product_id) REFERENCES bank_product (id) -- product_id의 외래 키 추가
);

-- Card 테이블 생성
CREATE TABLE IF NOT EXISTS core_card
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT       NOT NULL,
    account_id  BIGINT       NOT NULL,
    card_name   VARCHAR(255) NOT NULL,
    card_number VARCHAR(255) NOT NULL UNIQUE,
    cvc         VARCHAR(4)   NOT NULL,
    is_issued   BOOLEAN      NOT NULL,
    expired_at  DATETIME     NOT NULL,
    card_status BOOLEAN      NOT NULL,
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES bank_member (id),
    FOREIGN KEY (account_id) REFERENCES core_account (id)
);

-- Subscribe 테이블 생성
CREATE TABLE IF NOT EXISTS subscribe
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    bank_id      BIGINT         NOT NULL,
    product_name VARCHAR(255)   NOT NULL,
    business_num VARCHAR(255)    NOT NULL,
    company_name VARCHAR(255)   NOT NULL,
    expire_date  DATETIME       NOT NULL,
    is_subscribe BOOLEAN        NOT NULL,
    fee_amount   DECIMAL(38, 2) NOT NULL, -- fee_amount 정밀도를 38,2로 변경
    access_key   VARCHAR(255)   NOT NULL UNIQUE,
    secret_key   VARCHAR(255)   NOT NULL,
    created_at   DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (bank_id) REFERENCES bank (id)
);

-- CoreTransaction 테이블 생성
CREATE TABLE IF NOT EXISTS transaction
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    status     VARCHAR(20) NOT NULL,
    created_at DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- History 테이블 생성
CREATE TABLE IF NOT EXISTS core_history
(
    id                       BIGINT PRIMARY KEY AUTO_INCREMENT,
    core_account_id          BIGINT         NOT NULL,
    core_card                BIGINT,
    tran_type                VARCHAR(20)    NOT NULL,
    tran_amt                 DECIMAL(38, 2) NOT NULL, -- tran_amt 정밀도를 38,2로 변경
    after_balance_amt        DECIMAL(38, 2) NOT NULL, -- after_balance_amt 정밀도를 38,2로 변경
    counterparty_Name        VARCHAR(255),
    counterparty_account_num VARCHAR(255)   NOT NULL, -- counterparty_account_num 255자로 확장 및 NOT NULL 추가
    counterparty_bank_code   VARCHAR(255)   NOT NULL, -- counterparty_bank_code 255자로 확장 및 NOT NULL 추가
    description              VARCHAR(50)             DEFAULT '',
    created_at               DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at               DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    core_transaction_id      BIGINT,
    FOREIGN KEY (core_transaction_id) REFERENCES transaction (id),
    FOREIGN KEY (core_account_id) REFERENCES core_account (id),
    FOREIGN KEY (core_card) REFERENCES core_card (id)
);


