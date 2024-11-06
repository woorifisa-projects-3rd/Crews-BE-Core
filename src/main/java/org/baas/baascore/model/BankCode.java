package org.baas.baascore.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "bank")
public class BankCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String bankCode;

    @Column(name = "name")
    private String bankName;

}
