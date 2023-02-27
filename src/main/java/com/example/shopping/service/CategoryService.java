package com.example.shopping.service;

import com.example.shopping.domain.Category;
import com.example.shopping.exception.NotFoundException;
import com.example.shopping.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public Category findById(Integer id) {
        return get(id);
    }

    private Category get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category", id));
    }
}
