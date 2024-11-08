-- 데이터베이스 삭제 스크립트
DROP DATABASE IF EXISTS baascore;

-- 데이터베이스 생성 스크립트
CREATE DATABASE baascore;

USE baascore;

-- 테이블 생성 스크립트

-- Bank 테이블 생성
CREATE TABLE bank (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      code VARCHAR(20) NOT NULL,
                      name VARCHAR(255) NOT NULL,
                      created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Customer 테이블 생성
CREATE TABLE bank_member (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             email VARCHAR(255) NOT NULL UNIQUE,
                             name VARCHAR(255) NOT NULL,
                             jumin_number VARCHAR(255) NOT NULL UNIQUE,
                             gender CHAR(1) NOT NULL,
                             phone VARCHAR(15) NOT NULL UNIQUE,
                             address VARCHAR(255) NOT NULL,
                             birth DATE NOT NULL,
                             identity VARCHAR(50),
                             created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Account 테이블 생성
CREATE TABLE core_account (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              customer_id BIGINT NOT NULL,
                              bank_code_id BIGINT NOT NULL,
                              account_number VARCHAR(20) NOT NULL UNIQUE,
                              balance DECIMAL(15,2) NOT NULL,
                              currency VARCHAR(10),
                              account_type VARCHAR(20),
                              fintech_use_num VARCHAR(20) NOT NULL UNIQUE,
                              created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              FOREIGN KEY (customer_id) REFERENCES bank_member(id),
                              FOREIGN KEY (bank_code_id) REFERENCES bank(id)
);

-- Card 테이블 생성
CREATE TABLE core_card (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           customer_id BIGINT NOT NULL,
                           account_id BIGINT NOT NULL,
                           card_name VARCHAR(255) NOT NULL,
                           card_number VARCHAR(20) NOT NULL UNIQUE,
                           cvc VARCHAR(4) NOT NULL,
                           is_issued BOOLEAN NOT NULL,
                           expired_at VARCHAR(7) NOT NULL,
                           card_status BOOLEAN NOT NULL,
                           created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           FOREIGN KEY (customer_id) REFERENCES bank_member(id),
                           FOREIGN KEY (account_id) REFERENCES core_account(id)
);

-- Product 테이블 생성
CREATE TABLE bank_product (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              bank_id BIGINT NOT NULL,
                              name VARCHAR(255) NOT NULL,
                              rate DOUBLE NOT NULL,
                              created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              FOREIGN KEY (bank_id) REFERENCES bank(id)
);

-- Subscribe 테이블 생성
CREATE TABLE subscribe (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           bank_id BIGINT NOT NULL,
                           product_name VARCHAR(255),
                           business_num VARCHAR(20) NOT NULL,
                           company_name VARCHAR(255) NOT NULL,
                           expire_date DATETIME NOT NULL,
                           is_subscribe BOOLEAN NOT NULL,
                           fee_amount DECIMAL(15,2) NOT NULL,
                           access_key VARCHAR(255) NOT NULL,
                           secret_key VARCHAR(255) NOT NULL,
                           created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           FOREIGN KEY (bank_id) REFERENCES bank(id)
);

-- History 테이블 생성
CREATE TABLE core_history (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              core_account_id BIGINT NOT NULL,
                              core_card BIGINT,
                              tran_type VARCHAR(20) NOT NULL,
                              tran_amt DECIMAL(15,2) NOT NULL,
                              after_balance_amt DECIMAL(15,2) NOT NULL,
                              withdraw_name VARCHAR(255),
                              withdraw_account_num VARCHAR(20),
                              description VARCHAR(50),
                              created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              FOREIGN KEY (core_account_id) REFERENCES core_account(id),
                              FOREIGN KEY (core_card) REFERENCES core_card(id)
);

-- CoreTransaction 테이블 생성
CREATE TABLE transaction (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             account_history_id BIGINT NOT NULL,
                             status VARCHAR(20) NOT NULL,
                             created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             FOREIGN KEY (account_history_id) REFERENCES core_history(id)
);
