package com.example.shopping.controller;

import com.example.shopping.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("catalogs")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService service;
}
