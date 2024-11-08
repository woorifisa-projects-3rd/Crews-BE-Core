package org.baas.baascore.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountInfoResponse {
    private AccountIssuedResponse accountList;
    private CardListDTO cardList;
}
