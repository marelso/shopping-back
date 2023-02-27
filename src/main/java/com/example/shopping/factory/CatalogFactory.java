package com.example.shopping.factory;

import com.example.shopping.domain.Catalog;
import org.springframework.stereotype.Component;

@Component
public class CatalogFactory {
    public Catalog from(Catalog catalog, Catalog request) {
        if(request.getName() != null) {
            catalog.setName(request.getName());
        }
        if(request.getDescription() != null) {
            catalog.setDescription(request.getDescription());
        }

        return catalog;
    }
}
