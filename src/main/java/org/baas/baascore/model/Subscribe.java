package org.baas.baascore.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.baas.baascore.util.BaseTimeEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 거래 내역을 관리하는 History 엔티티
 */
@Getter
@Entity
@Table(name = "subscribe")
public class Subscribe extends BaseTimeEntity {
    // 구독 정보의 고유 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 상품 주인 은행 (은행:상품 1:N)
    @ManyToOne
    private Bank bank;

    // 구독상품명
    private String productName;

    // 사업자 등록 번호
    @Column(name = "business_num", nullable = false)
    private String businessNum;

    // 구독회사명
    @Column(name = "company_name", nullable = false)
    private String companyName;

    // 구독만료일자
    @Column(name = "expire_date", nullable = false)
    private LocalDateTime expireDate;

    // 현재 구독여부
    @Column(name = "is_subscribe", nullable = false)
    private boolean isSubscribe;

    // 구독료
    @Column(name = "fee_amount", nullable = false)
    private BigDecimal feeAmt;

    // 해당 회사에 발급한 엑세스 키
    @Column(name = "access_key", nullable = false)
    private String accessKey;

    // 해당 회사에 발급한 시크릿 키
    @Column(name = "secret_key", nullable = false)
    private String secretKey;

}
