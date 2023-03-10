package com.example.shopping.repository;

import com.example.shopping.domain.OfferCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferCategoryRepository extends JpaRepository<OfferCategory, Integer> {
    List<OfferCategory> findAllByOffersId(Integer id);
    List<OfferCategory> findAllByCategoryId(Integer id);

    void deleteByOffersId(Integer offersId);
}
