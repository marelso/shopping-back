package com.example.shopping.service;

import com.example.shopping.repository.OfferCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferCategoryService {
    private final OfferCategoryRepository repository;
}
