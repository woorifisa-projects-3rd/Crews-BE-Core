package org.baas.baascore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.baas.baascore.util.AccountType;
import org.baas.baascore.util.BaseTimeEntity;
import org.baas.baascore.util.CurrencyType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/***
 * Baas Core 의 계좌 엔티티
 */
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "core_account")
public class Account extends BaseTimeEntity {
    // 계좌의 고유 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 한 고객은 여러 계좌를 소유할수있음
    // 계좌 소유 고객 정보
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    // 한 계좌는 하나의 은행 코드와만 연결됨
    // 계좌가 소속된 은행 정보
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_code_id", referencedColumnName = "id", nullable = false)
    private Bank bank;

    // 계좌 번호, 시스템 내에서 고유함
    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    // 계좌 잔액, 0 이상만 허용됨
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    // 계좌가 사용하는 통화 종류 (예: USD, KRW)
    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private CurrencyType currencyType;

    // 계좌 유형 (예: 개인, 모임)
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    // 클라이언트(서비스)와 사용자(고객) 쌍의 식별번호
    @Column(name = "fintech_use_num",nullable = false, unique = true)
    private String fintechUseNum;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean isDeleted;

    public void accountDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    // 한 계좌가 여러 카드를 소유할 수 있도록 양방향 매핑
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Card> cards = new ArrayList<>();
}