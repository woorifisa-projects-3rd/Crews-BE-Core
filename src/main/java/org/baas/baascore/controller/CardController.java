package org.baas.baascore.controller;

import lombok.RequiredArgsConstructor;
import org.baas.baascore.service.CardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/cards")
public class CardController {
    private final CardService cardService;

}
