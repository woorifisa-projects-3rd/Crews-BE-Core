package org.baas.baascore.service;

import lombok.RequiredArgsConstructor;
import org.baas.baascore.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
}
