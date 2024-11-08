package org.baas.baascore.excaption;

import lombok.Getter;

@Getter
public class CustomerNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomerNotFoundException() {
        super(ErrorCode.CUSTOMER_NOT_FOUND.getMessage());
        this.errorCode = ErrorCode.CUSTOMER_NOT_FOUND;
    }
}
