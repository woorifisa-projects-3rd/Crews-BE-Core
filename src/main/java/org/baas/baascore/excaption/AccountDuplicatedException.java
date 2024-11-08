package org.baas.baascore.excaption;

public class AccountDuplicatedException extends RuntimeException {
    private final ErrorCode errorCode;

    public AccountDuplicatedException() {
        super(ErrorCode.ACCOUNTNUMBER_DUPLICATED.getMessage());
        this.errorCode = ErrorCode.ACCOUNTNUMBER_DUPLICATED;
    }
}
