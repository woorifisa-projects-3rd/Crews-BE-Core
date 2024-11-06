package org.baas.baascore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "bank_member")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "jumin_number")
    private String juminNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phoneNum;

    @Column(name = "address")
    private String address;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "identiy")
    private LocalDate identiyCode;
}
