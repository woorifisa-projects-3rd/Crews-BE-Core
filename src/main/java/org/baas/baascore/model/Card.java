package org.baas.baascore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.baas.baascore.util.BaseTimeEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

/**
 * Card 엔티티 - 카드 정보 관리
 */
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "core_card")
public class Card extends BaseTimeEntity {
    // 카드의 고유 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 카드 소유주
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // 카드를 소유한 계좌, 반드시 존재해야 함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // 카드 이름 (예: 우리트래블카드, 카카오모임카드 등)
    @Column(name = "card_name", nullable = false)
    private String cardName;

    // 카드 번호 (예: 4862-6842-1836-4591)
    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    // // 카드 CVC 코드 (보안 코드 직불카드에 기재된 3~4자리 숫자)
    @Column(name = "cvc", nullable = false)
    private String cvc;

    // 실물 카드 발급 여부
    @Column(name = "is_issued", nullable = false)
    private boolean isIssued;

    // 카드 만료일 (MM/YY 형식)
    @Column(name = "expired_at", nullable = false)
    private LocalDateTime expiredAt;

    // 카드 활성 상태 (활성화 또는 비활성화)
    @Column(name = "card_status", nullable = false)
    private boolean cardStatus;
}
