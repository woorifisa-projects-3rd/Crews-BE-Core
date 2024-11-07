package org.baas.baascore.service;

import lombok.RequiredArgsConstructor;
import org.baas.baascore.repository.SubscribeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;
}
