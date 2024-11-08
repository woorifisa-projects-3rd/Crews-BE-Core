package org.baas.baascore.excaption;

public class MemberNotEqualsException extends RuntimeException{
    private final ErrorCode errorCode;

    public MemberNotEqualsException() {
        super(ErrorCode.MEMBER_NOT_EQUALS.getMessage());
        this.errorCode = ErrorCode.MEMBER_NOT_EQUALS;
    }
}

