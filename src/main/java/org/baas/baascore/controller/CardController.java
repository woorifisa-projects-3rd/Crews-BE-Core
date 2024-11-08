package org.baas.baascore.controller;

import lombok.RequiredArgsConstructor;
import org.baas.baascore.dto.CardIssuedResponse;
import org.baas.baascore.dto.CommonRequest;
import org.baas.baascore.dto.IdentityRequest;
import org.baas.baascore.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/cards")
public class CardController {
    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CardIssuedResponse> cardIssued(@RequestBody CommonRequest commonRequest){
        return ResponseEntity.ok().body(cardService.cardIssued(commonRequest));
    }

}
