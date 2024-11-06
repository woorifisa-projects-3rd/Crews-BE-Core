package org.baas.baascore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.baas.baascore.util.CurrencyType;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "core_acount")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 한 계좌는 한 명의 고객과만 연결됨
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    // 한 계좌는 하나의 은행 코드와만 연결됨
    @OneToOne
    @JoinColumn(name = "bank_code_id", referencedColumnName = "id")
    private BankCode bankCode;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private BigInteger balance;


    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "upadted_at")
    private LocalDate upadtedAt;

    @Enumerated(EnumType.STRING) // ENUM을 문자열로 저장
    @Column(name = "currency")
    private CurrencyType currencyType;

    @Enumerated(EnumType.STRING) // ENUM을 문자열로 저장
    @Column(name = "account_type")
    private CurrencyType accountType;

    @Column(name = "fintech_use_num")
    private String fintechUseNum;
}