package org.baas.baascore.excaption;

import lombok.Getter;

@Getter
public class BalanceNotZeroException  extends RuntimeException {
    private final ErrorCode errorCode;

    public BalanceNotZeroException() {
        super(ErrorCode.BALANCE_NOT_ZERO.getMessage());
        this.errorCode = ErrorCode.BALANCE_NOT_ZERO;
    }
}
