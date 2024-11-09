package org.baas.baascore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonRequest {

    @NotBlank
    private String identityCode;

    @NotBlank
    private String fintechUseNum;
}
