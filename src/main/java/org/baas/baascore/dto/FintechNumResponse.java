package org.baas.baascore.dto;

import lombok.Builder;
import lombok.Getter;
import org.baas.baascore.model.Account;
import org.baas.baascore.util.AccountType;

@Getter
@Builder
public class FintechNumResponse {
    private String memberName;
    private String identityCode;
    private AccountType accountType;
    private String bankCode;
    private String bankName;
    private String accountNumber;
    private String fintechUseNum;


    public static FintechNumResponse from(Account account){
        return FintechNumResponse.builder()
                .memberName(account.getCustomer().getName())
                .identityCode(account.getCustomer().getIdentityCode())
                .accountType(account.getAccountType())
                .bankCode(account.getBank().getBankCode())
                .bankName(account.getBank().getBankName())
                .accountNumber(account.getAccountNumber())
                .fintechUseNum(account.getFintechUseNum())
                .build();
    }
}
