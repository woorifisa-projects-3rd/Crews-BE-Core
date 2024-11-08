package org.baas.baascore.service;

import lombok.RequiredArgsConstructor;

import org.baas.baascore.repository.TransactionHistoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionHistoryService {
    private final TransactionHistoryRepository transactionHistoryRepository;
}
