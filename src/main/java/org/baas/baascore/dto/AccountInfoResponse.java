package org.baas.baascore.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class AccountInfoResponse {
    private List<AccountIssuedResponse> accountList = new ArrayList<>();
    private List<CardListDTO> cardList = new ArrayList<>();
}
