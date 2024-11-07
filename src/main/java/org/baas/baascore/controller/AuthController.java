package org.baas.baascore.controller;

import lombok.RequiredArgsConstructor;
import org.baas.baascore.service.SubscribeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final SubscribeService subscribeService;

}
