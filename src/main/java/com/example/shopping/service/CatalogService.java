package com.example.shopping.service;

import com.example.shopping.domain.Catalog;
import com.example.shopping.exception.NotFoundException;
import com.example.shopping.factory.CatalogFactory;
import com.example.shopping.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogRepository repository;
    private final CatalogFactory factory;

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

    public Catalog update(Integer id, Catalog request) {
        var catalog = get(id);

        return repository.save(factory.from(catalog, request));
    }

    public void delete(Integer id) {
        //TODO The categories module will have a FK-like reference to the catalog entity.
        // When deleting a catalog with reference, the database will throw an exception.
        // I think the ideal is to prevent this exception.

        repository.deleteById(id);
    }
}
