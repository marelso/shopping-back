package com.example.shopping.service;

import com.example.shopping.domain.Offer;
import com.example.shopping.exception.NotFoundException;
import com.example.shopping.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository repository;

    public Offer findById(Integer id) {
        return get(id);
    }

    private Offer get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Offer", id));
    }
}
