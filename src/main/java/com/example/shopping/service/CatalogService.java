package com.example.shopping.service;

import com.example.shopping.domain.Catalog;
import com.example.shopping.exception.NotFoundException;
import com.example.shopping.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogRepository repository;

    public Catalog findById(Integer id) {
        return get(id);
    }

    private Catalog get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Catalog", id));
    }

    public Catalog create(Catalog request) {
        return repository.save(request);
    }

    public List<Catalog> list() {
        return repository.findAll();
    }
}
