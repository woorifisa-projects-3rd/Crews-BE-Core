package org.baas.baascore.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CardListDTO {
    private String memberName;
    private String bankCode;
    private String bankName;
    private String accountNumber;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String cardNumber;
}
