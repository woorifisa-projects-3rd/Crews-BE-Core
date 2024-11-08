package org.baas.baascore.excaption;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HashingAlgorithmNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleHashingAlgorithmNotFoundException(HashingAlgorithmNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder()
                .errorCode(ex.getErrorCode().name())
                .message(ex.getErrorCode().getMessage())
                .details(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build());


    }
    @ExceptionHandler(BankNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBankNotFoundException(BankNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                .errorCode(ex.getErrorCode().name())
                .message(ex.getErrorCode().getMessage())
                .details(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                .errorCode(ex.getErrorCode().name())
                .message(ex.getErrorCode().getMessage())
                .details(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(IdentityCodeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleIdentityCodeNotFoundException(BankNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder()
                .errorCode(ex.getErrorCode().name())
                .message(ex.getErrorCode().getMessage())
                .details(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(FintechNumberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFintechCodeNotFoundException(BankNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder()
                .errorCode(ex.getErrorCode().name())
                .message(ex.getErrorCode().getMessage())
                .details(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(MemberNotEqualsException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotEqualsException(BankNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder()
                .errorCode(ex.getErrorCode().name())
                .message(ex.getErrorCode().getMessage())
                .details(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(BalanceNotZeroException.class)
    public ResponseEntity<ErrorResponse> handleBalanceNotZeroException(BankNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder()
                .errorCode(ex.getErrorCode().name())
                .message(ex.getErrorCode().getMessage())
                .details(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }
    @ExceptionHandler(AccountNumberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNumberNotFoundException(BankNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder()
                .errorCode(ex.getErrorCode().name())
                .message(ex.getErrorCode().getMessage())
                .details(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(AccountDuplicatedException.class)
    public ResponseEntity<ErrorResponse> handleAccountNumberDuplicatedException(BankNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder()
                .errorCode(ex.getErrorCode().name())
                .message(ex.getErrorCode().getMessage())
                .details(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }
}
