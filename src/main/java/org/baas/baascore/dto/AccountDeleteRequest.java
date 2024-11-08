package org.baas.baascore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountDeleteRequest {
    @NotBlank
    private String identiyCode;

    @NotBlank
    private String fintechUseNum;
}
