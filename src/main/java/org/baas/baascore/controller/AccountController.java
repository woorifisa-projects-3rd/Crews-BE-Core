package org.baas.baascore.controller;

import lombok.RequiredArgsConstructor;
import org.baas.baascore.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
    private final AccountService accountService;
}
