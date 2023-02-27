package com.example.shopping.service;

import com.example.shopping.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogRepository repository;
}
