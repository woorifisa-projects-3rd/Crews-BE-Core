package org.baas.baascore.excaption;

import lombok.Getter;

@Getter
public class BankNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public BankNotFoundException() {
        super(ErrorCode.BANK_NOT_FOUND.getMessage());
        this.errorCode = ErrorCode.BANK_NOT_FOUND;
    }
}
