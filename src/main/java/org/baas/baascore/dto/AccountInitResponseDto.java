package org.baas.baascore.dto;

import lombok.Builder;
import lombok.Getter;
import org.baas.baascore.model.Account;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class AccountInitResponseDto {
    private String customerName;
    private String bankName;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public AccountInitResponseDto(String customerName, String bankName, String accountNumber, String accountType,
                                  BigDecimal balance, LocalDate createdAt, LocalDate updatedAt) {
        this.customerName = customerName;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Account 엔티티를 AccountInitResponseDto로 변환하는 정적 메서드 추가
    public static AccountInitResponseDto fromEntity(Account account) {
        return AccountInitResponseDto.builder()
                .customerName(account.getCustomer().getName())
                .bankName(account.getBank().getBankName())
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType().name())
                .balance(account.getBalance())
                .createdAt(account.getCreatedAt().toLocalDate())
                .updatedAt(account.getUpdatedAt().toLocalDate())
                .build();
    }
}
