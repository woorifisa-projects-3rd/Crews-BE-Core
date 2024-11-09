package org.baas.baascore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubscribeResponseDto {
    private String accessKey;
    private String secretKey;

    public SubscribeResponseDto(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }
}
