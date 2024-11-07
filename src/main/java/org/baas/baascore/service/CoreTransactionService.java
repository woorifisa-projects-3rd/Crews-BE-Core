package org.baas.baascore.service;

import lombok.RequiredArgsConstructor;
import org.baas.baascore.repository.CoreTransactionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoreTransactionService {
    private final CoreTransactionRepository coreTransactionRepository;
}
