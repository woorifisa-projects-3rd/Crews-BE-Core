package org.baas.baascore.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.baas.baascore.util.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 은행 정보를 저장하는 BankCode 엔티티
 */
@Getter
@Entity
@Table(name = "bank")
public class Bank extends BaseTimeEntity {
    // 은행 정보의 고유 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 은행 코드 (예: 020, 090)
    @Column(name = "code", nullable = false)
    private String bankCode;

    // 은행 이름 (예: 하나은행, 국민은행)
    @Column(name = "name", nullable = false)
    private String bankName;

    // 해당 은행사의 상품 리스트
    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
    // 각 은행이 서비스중인 api 구독 서비스 리스트
    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    private List<Subscribe> subscriptions = new ArrayList<>();
}
