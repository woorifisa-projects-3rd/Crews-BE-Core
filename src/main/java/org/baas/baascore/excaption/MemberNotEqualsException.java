package org.baas.baascore.excaption;

import lombok.Getter;

@Getter
public class MemberNotEqualsException extends RuntimeException{
    private final ErrorCode errorCode;

    public MemberNotEqualsException() {
        super(ErrorCode.MEMBER_NOT_EQUALS.getMessage());
        this.errorCode = ErrorCode.MEMBER_NOT_EQUALS;
    }
}

