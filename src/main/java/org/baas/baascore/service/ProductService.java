package org.baas.baascore.service;

import lombok.RequiredArgsConstructor;
import org.baas.baascore.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

}
