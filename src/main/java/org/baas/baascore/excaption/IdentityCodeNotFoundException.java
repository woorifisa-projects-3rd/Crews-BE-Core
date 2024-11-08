package org.baas.baascore.excaption;

import lombok.Getter;

@Getter
public class IdentityCodeNotFoundException  extends RuntimeException {
    private final ErrorCode errorCode;

    public IdentityCodeNotFoundException() {
        super(ErrorCode.IDENTITYCODE_NOT_FOUND.getMessage());
        this.errorCode = ErrorCode.IDENTITYCODE_NOT_FOUND;
    }
}
