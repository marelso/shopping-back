package com.example.shopping.service;

import com.example.shopping.domain.Category;
import com.example.shopping.exception.NotFoundException;
import com.example.shopping.factory.CategoryFactory;
import com.example.shopping.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryFactory factory;

    public Category findById(Integer id) {
        return get(id);
    }

    private Category get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category", id));
    }

    public List<Category> list(Integer catalogId) {
        return catalogId == null ? repository.findAll()
                : repository.findAllByCatalogId(catalogId);
    }

    public Category create(Category request) {
        return repository.save(request);
    }

    public Category update(Integer id, Category request) {
        var category = get(id);

        return repository.save(factory.from(category, request));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
