package org.baas.baascore.model;

import jakarta.persistence.*;
import lombok.Getter;

/***
 * 은행의 상품 엔티티
 */
@Getter
@Entity
@Table(name = "bank_product")
public class Product {
    // 상품 고유 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 상품 주인 은행 (은행:상품 1:N)
    @ManyToOne
    private Bank bank;

    // 상품 이름
    @Column(name= "name",nullable = false)
    private String productName;

    // 상품 이자율
    @Column(name= "rate",nullable = false)
    private double rate;
}
