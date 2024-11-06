package org.baas.baascore.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="core_card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    Account account;
}
