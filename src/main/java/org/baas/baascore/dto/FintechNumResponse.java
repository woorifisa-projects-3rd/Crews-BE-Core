package org.baas.baascore.dto;

import lombok.Builder;
import lombok.Getter;
import org.baas.baascore.model.Account;
import org.baas.baascore.util.AccountType;

@Getter
@Builder
public class FintechNumResponse {
    private String memberName;
    private String identiyCode;
    private AccountType accountType;
    private String bankCode;
    private String bankName;
    private String accountNumber;
    private String finUseNum;


    public static FintechNumResponse of(Account account){
        return FintechNumResponse.builder()
                .memberName(account.getCustomer().getName())
                .identiyCode(account.getCustomer().getIdentityCode())
                .accountType(account.getAccountType())
                .bankCode(account.getBank().getBankCode())
                .bankName(account.getBank().getBankName())
                .accountNumber(account.getAccountNumber())
                .finUseNum(account.getFintechUseNum())
                .build();
    }
}
