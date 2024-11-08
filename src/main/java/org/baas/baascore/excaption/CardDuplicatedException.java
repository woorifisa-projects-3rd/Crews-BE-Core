package org.baas.baascore.excaption;

import lombok.Getter;

@Getter
public class CardDuplicatedException extends RuntimeException {
    private final ErrorCode errorCode;

    public CardDuplicatedException() {
        super(ErrorCode.CARDNUMBER_DUPLICATED.getMessage());
        this.errorCode = ErrorCode.CARDNUMBER_DUPLICATED;
    }
}
