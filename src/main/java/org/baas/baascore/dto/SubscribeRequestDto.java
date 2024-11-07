package org.baas.baascore.dto;

public record SubscribeRequestDto(
        Long bankId,
        String productName,
        String businessNum,
        String companyName
) {
}
