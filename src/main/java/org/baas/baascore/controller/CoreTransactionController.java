package org.baas.baascore.controller;

import lombok.RequiredArgsConstructor;
import org.baas.baascore.service.CoreTransactionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/transfer")
public class CoreTransactionController {
    private final CoreTransactionService coreTransactionService;
}
