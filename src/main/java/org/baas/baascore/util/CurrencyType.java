package org.baas.baascore.util;

public enum CurrencyType {
    KRW("KRW", "₩", "원화"),
    USD("USD", "$", "달러"),
    JPY("JPY", "¥", "엔화"),
    CNY("CNY", "¥", "위안");

    private final String code;
    private final String symbol;
    private final String description;

    CurrencyType(String code, String symbol, String description) {
        this.code = code;
        this.symbol = symbol;
        this.description = description;
    }
}
