package org.baas.baascore.excaption;

import lombok.Getter;

@Getter
public class AccountDuplicatedException extends RuntimeException {
    private final ErrorCode errorCode;

    public AccountDuplicatedException() {
        super(ErrorCode.ACCOUNTNUMBER_DUPLICATED.getMessage());
        this.errorCode = ErrorCode.ACCOUNTNUMBER_DUPLICATED;
    }
}
