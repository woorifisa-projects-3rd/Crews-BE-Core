package org.baas.baascore.excaption;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class ErrorResponse {
    private String errorCode;
    private String message;
    private String details;
    private LocalDateTime timestamp;
}
