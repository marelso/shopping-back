package com.example.shopping.controller;

import com.example.shopping.domain.Catalog;
import com.example.shopping.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("catalogs")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService service;

    @GetMapping
    public List<Catalog> get() {
        return service.list();
    }

    @GetMapping("/{id}")
    public Catalog get(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Catalog post(@RequestBody Catalog request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public Catalog put(
            @PathVariable Integer id,
            @RequestBody Catalog request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
