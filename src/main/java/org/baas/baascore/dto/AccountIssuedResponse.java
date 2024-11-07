package org.baas.baascore.dto;

import lombok.Builder;
import lombok.Getter;
import org.baas.baascore.model.Account;
import org.baas.baascore.util.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class AccountIssuedResponse {
    private String memberName;
    private String identiyCode;
    private AccountType accountType;
    private String bankCode;
    private String bankName;
    private String accountNumber;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private BigDecimal balance;
    private String finUseNum;


    public static AccountIssuedResponse of(Account account){
        return AccountIssuedResponse.builder()
                .memberName(account.getCustomer().getName())
                .identiyCode(account.getCustomer().getIdentiyCode())
                .accountType(account.getAccountType())
                .bankCode(account.getBank().getBankCode())
                .bankName(account.getBank().getBankName())
                .accountNumber(account.getAccountNumber())
                .createAt(account.getCreatedAt())
                .updateAt(account.getUpdatedAt())
                .balance(account.getBalance())
                .finUseNum(account.getFintechUseNum())
                .build();
    }
}
