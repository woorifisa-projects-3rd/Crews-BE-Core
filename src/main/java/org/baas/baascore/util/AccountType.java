package org.baas.baascore.util;

public enum AccountType {
    PERSONAL("개인"),
    CREW("모임");


    private final String type;       // 통화 코드

    AccountType(String type) {
        this.type = type;
    }
}
