package com.example.shopping.controller;

import com.example.shopping.domain.Catalog;
import com.example.shopping.service.CatalogService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("catalogs")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "List all existing catalogs.")
    public List<Catalog> get() {
        return service.list();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "List the requested catalog if it exists.")
    public Catalog get(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new catalog", notes = "Catalogs can be referenced by categories.")
    public Catalog post(@RequestBody Catalog request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update an existing catalog.")
    public Catalog put(
            @PathVariable Integer id,
            @RequestBody Catalog request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete an existing catalog.")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
