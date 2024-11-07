package org.baas.baascore.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.baas.baascore.util.BaseTimeEntity;

import java.time.LocalDate;

/**
 * 고객 정보를 관리하는 Customer 엔티티
 */
@Getter
@Entity
@Table(name = "bank_member")
public class Customer extends BaseTimeEntity {
    // 고객 고유 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 고객 이메일, 시스템 내에서 고유해야 함
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // 고객 이름
    @Column(name = "name", nullable = false)
    private String name;

    // 고객 주민번호, 고유해야 함
    @Column(name = "jumin_number", nullable = false, unique = true)
    private String juminNumber;

    // 고객 성별 (예: M/F)
    @Column(name = "gender", nullable = false)
    private String gender;

    // 고객 전화번호, 고유해야 함
    @Column(name = "phone", nullable = false, unique = true)
    private String phoneNum;

    // 고객 주소
    @Column(name = "address", nullable = false)
    private String address;

    // 고객 생년월일
    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    // 동일인 식별 코드
    @Column(name = "identiy")
    private LocalDate identiyCode;
}
