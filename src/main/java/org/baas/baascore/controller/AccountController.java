package org.baas.baascore.controller;

import lombok.RequiredArgsConstructor;
import org.baas.baascore.dto.*;
import org.baas.baascore.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountIssuedResponse> accountIssued(@RequestBody AccountIssuedRequest accountIssuedRequest){
        return ResponseEntity.ok().body(accountService.accountIssued(accountIssuedRequest));

    }

    @DeleteMapping
    public ResponseEntity<AccountDeleteResponse> accountDelete(@RequestBody AccountDeleteRequest accountDeleteRequest){
        return ResponseEntity.ok().body(accountService.accountDelete(accountDeleteRequest));

    }

    @PostMapping("/info")
    public ResponseEntity<AccountInfoResponse> accountInfo(@RequestBody AccountInfoRequest accountInfoRequest) {
        return ResponseEntity.ok().body(accountService.accountInfo(accountInfoRequest));

    }


    @PostMapping("/fin-num")
    public ResponseEntity<FintechNumResponse> fintechNum(@RequestBody FintechNumRequest fintechNumRequest){
        return ResponseEntity.ok().body(accountService.fintechNum(fintechNumRequest));
    }





}
