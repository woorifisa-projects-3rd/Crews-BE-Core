package org.baas.baascore.dto;

import lombok.Builder;
import lombok.Getter;
import org.baas.baascore.model.Card;

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

    public static CardListDTO of(Card card){
        return CardListDTO.builder()
                .memberName(card.getCustomer().getName())
                .bankCode(card.getAccount().getBank().getBankCode())
                .bankName(card.getAccount().getBank().getBankName())
                .accountNumber(card.getAccount().getAccountNumber())
                .createAt(card.getCreatedAt())
                .updateAt(card.getUpdatedAt())
                .cardNumber(card.getCardNumber())
                .build();
    }
}
