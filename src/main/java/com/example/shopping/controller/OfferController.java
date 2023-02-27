package com.example.shopping.controller;

import com.example.shopping.domain.requests.OfferRequest;
import com.example.shopping.dtos.OfferDTO;
import com.example.shopping.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
public class OfferController {
    private OfferService service;

    @GetMapping
    public List<OfferDTO> get() {
        return service.list();
    }

    @GetMapping("/{id}")
    public OfferDTO get(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public OfferDTO post(@RequestBody OfferRequest offer) {
        return service.create(offer);
    }

    @PutMapping("/{id}")
    public OfferDTO put(@PathVariable Integer id
            , @RequestBody OfferRequest offer) {

        return service.update(id, offer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
