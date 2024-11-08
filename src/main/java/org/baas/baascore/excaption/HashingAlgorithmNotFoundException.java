package org.baas.baascore.excaption;

import lombok.Getter;

@Getter
public class HashingAlgorithmNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public HashingAlgorithmNotFoundException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public HashingAlgorithmNotFoundException(ErrorCode errorCode) {
        this(errorCode, errorCode.getMessage());
    }
}
