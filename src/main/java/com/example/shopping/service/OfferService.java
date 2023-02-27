package com.example.shopping.service;

import com.example.shopping.domain.Category;
import com.example.shopping.domain.Coupon;
import com.example.shopping.domain.Offer;
import com.example.shopping.domain.OfferCategory;
import com.example.shopping.domain.requests.OfferRequest;
import com.example.shopping.dtos.OfferDTO;
import com.example.shopping.exception.NotFoundException;
import com.example.shopping.factory.OfferFactory;
import com.example.shopping.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository repository;
    private final OfferFactory factory;
    private final CategoryService categoryService;
    private final CouponService couponService;
    private final OfferCategoryService relationService;

    public List<OfferDTO> list() {
        return repository.findAll()
                .stream()
                .map((o) -> {
                    List<Category> categories = new ArrayList<>();
                    List<OfferCategory> relatedCategories = relationService.findAllByOffersId(o.getId());
                    relatedCategories.forEach((r) -> categories.add(categoryService.findById(r.getCategoryId())));
                    return factory.from(o
                            , o.getCouponId() != null ? couponService.findById(o.getCouponId()) : null
                            , categories);
                })
                .collect(Collectors.toList());
    }

    public OfferDTO findById(Integer id) {
        var offer = get(id);

        List<Category> categories = new ArrayList<>();
        List<OfferCategory> relatedCategories = relationService.findAllByOffersId(id);
        relatedCategories.forEach((r) -> categories.add(categoryService.findById(r.getCategoryId())));

        return factory.from(offer
                , couponService.findById(offer.getCouponId())
                , categories);
    }

    private Offer get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Offer", id));
    }

    public OfferDTO create(OfferRequest request) {
        if(request.getCategories().isEmpty()) {
            throw new RuntimeException("Offer must have an category.");
        }

        OfferDTO response;
        Offer offer;
        Coupon coupon = null;

        if(request.getCoupon() != null && request.getCoupon().getId() == null) {
            coupon = couponService.create(request.getCoupon());
            offer = factory.from(request, coupon);
        }
        else if(request.getCoupon() != null && request.getCoupon().getId() != null){
            coupon = couponService.findById(request.getCoupon().getId());
            offer = factory.from(request, coupon);
        }
        else {
            offer = factory.from(request, null);
        }

        offer = repository.save(offer);

        List<Category> categories = new ArrayList<>();
        request.getCategories()
                .forEach((c) -> categories.add(categoryService.findById(c.getId())));

        for(Category category : categories) {
            var relation = new OfferCategory();
            relation.setOffersId(offer.getId());
            relation.setCategoryId(category.getId());
            relationService.save(relation);
        }

        response = factory.from(offer, coupon, categories);

        return response;
    }
}
