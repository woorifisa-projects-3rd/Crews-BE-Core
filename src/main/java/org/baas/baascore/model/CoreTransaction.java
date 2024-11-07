package org.baas.baascore.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.baas.baascore.util.BaseTimeEntity;
import org.baas.baascore.util.StatusType;

/**
 * 트랜잭션 엔티티 - History 엔티티와 연결된 트랜잭션 정보를 관리
 */
@Getter
@Entity
@Table(name = "transaction")
public class CoreTransaction extends BaseTimeEntity {
    // 트랜잭션 고유 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 트랜잭션과 연결된 거래 내역 정보
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_history_id", nullable = false)
    private History history;

    // 트랜잭션 상태 (예: 성공, 실패 등)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusType status;
}
