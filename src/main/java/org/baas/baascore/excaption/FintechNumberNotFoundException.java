package org.baas.baascore.excaption;

import lombok.Getter;

@Getter
public class FintechNumberNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;

    public FintechNumberNotFoundException() {
        super(ErrorCode.FINTECHCODE_NOT_FOUND.getMessage());
        this.errorCode = ErrorCode.FINTECHCODE_NOT_FOUND;
    }
}

