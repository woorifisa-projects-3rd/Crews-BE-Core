package org.baas.baascore.util;

public enum CurrencyType {
    KRW("KRW", "₩", "원화"),
    USD("USD", "$", "달러"),
    JPY("JPY", "¥", "엔화"),
    CNY("CNY", "¥", "위안");

    private final String code;       // 통화 코드
    private final String symbol;     // 통화 기호
    private final String description; // 통화 설명 (한글)

    CurrencyType(String code, String symbol, String description) {
        this.code = code;
        this.symbol = symbol;
        this.description = description;
    }
}
