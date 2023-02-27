package com.example.shopping.factory;

import com.example.shopping.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryFactory {
    public Category from(Category category, Category request) {
        if(request.getName() != null) {
            category.setName(request.getName());
        }
        if(request.getDescription() != null) {
            category.setDescription(request.getDescription());
        }
        if(request.getPriority() != null) {
            category.setPriority(request.getPriority());
        }
        if(request.getCatalogId() != null) {
            category.setCatalogId(request.getCatalogId());
        }

        return category;
    }
}
