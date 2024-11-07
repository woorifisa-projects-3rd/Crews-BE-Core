package org.baas.baascore.excaption;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    BANK_NOT_FOUND("해당하는 은행을 찾을 수 없습니다."),
    CUSTOMER_NOT_FOUND("해당하는 고객을 찾을 수 없습니다."),
    HASH_ALGORITHM_NOT_FOUND("해시 알고리즘을 찾을 수 없습니다.");

    private final String message;
}
