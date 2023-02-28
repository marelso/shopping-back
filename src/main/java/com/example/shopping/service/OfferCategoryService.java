package com.example.shopping.service;

import com.example.shopping.domain.OfferCategory;
import com.example.shopping.exception.NotFoundException;
import com.example.shopping.repository.OfferCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferCategoryService {
    private final OfferCategoryRepository repository;

    public List<OfferCategory> findAll() {
        return repository.findAll();
    }

    public OfferCategory findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Offer category relation", id));
    }

    public List<OfferCategory> findAllByOffersId(Integer id) {
        return repository.findAllByOffersId(id);
    }

    public OfferCategory save(OfferCategory offersCategories) {
        return repository.save(offersCategories);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteByOffersId(Integer id) {
        repository.deleteByOffersId(id);
    }
}
