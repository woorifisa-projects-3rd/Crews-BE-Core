package org.baas.baascore.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FintechNumRequest {

    @NotBlank
    private String identiyCode;

    @NotBlank
    @Min(10) @Max(14)
    private String accountNumber;

}
