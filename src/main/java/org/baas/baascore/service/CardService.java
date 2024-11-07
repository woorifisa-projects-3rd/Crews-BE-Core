package org.baas.baascore.service;

import lombok.RequiredArgsConstructor;
import org.baas.baascore.repository.CardRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
}
