package org.baas.baascore.util;

public enum StatusType {
    PENDING("개인"),
    SUCCESS("모임"),
    FAIL("실패");

    private final String type;

    StatusType(String type) {
        this.type = type;
    }
}
