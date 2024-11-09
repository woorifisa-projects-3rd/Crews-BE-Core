package org.baas.baascore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AccountInfoRequest {

    @NotBlank
    private String identityCode;
}
