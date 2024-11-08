package org.baas.baascore.excaption;

public class AccountNumberNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public AccountNumberNotFoundException() {
        super(ErrorCode.ACCOUNTNUMBER_NOT_FOUND.getMessage());
        this.errorCode = ErrorCode.ACCOUNTNUMBER_NOT_FOUND;
    }
}
