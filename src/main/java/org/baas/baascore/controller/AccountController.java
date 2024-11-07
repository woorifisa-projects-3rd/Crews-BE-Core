package org.baas.baascore.controller;

import lombok.RequiredArgsConstructor;
import org.baas.baascore.dto.AccountIssuedRequest;
import org.baas.baascore.dto.AccountIssuedResponse;
import org.baas.baascore.model.Account;
import org.baas.baascore.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public AccountIssuedResponse accountIssued(@RequestBody AccountIssuedRequest accountIssuedRequest,
                                               @RequestHeader("accessKey") String accessKey,
                                               @RequestHeader("secretKey") String secretKey){
        System.out.println("accountIssuedRequest = " + accountIssuedRequest);
        if(!validateKeys(accessKey,secretKey))
            throw new RuntimeException("accessKey와 secretKey를 확인 해 주세요");
        return accountService.accountIssued(accountIssuedRequest);

    }
}
