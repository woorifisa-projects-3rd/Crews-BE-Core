package org.baas.baascore.dto;

import lombok.Builder;
import lombok.Getter;
import org.baas.baascore.model.Card;

import java.time.LocalDateTime;

@Getter
@Builder
public class CardIssuedResponse {
    private String cardName;
    private String cardNumber;
    private LocalDateTime createAt;
    private LocalDateTime expiredAt;
    private String accountNumber;

    public static CardIssuedResponse from(Card card){
        return CardIssuedResponse.builder()
                .cardName(card.getCardName())
                .cardNumber(card.getCardNumber())
                .createAt(card.getCreatedAt())
                .expiredAt(card.getExpiredAt())
                .accountNumber(card.getAccount().getAccountNumber())
                .build();
    }
}
