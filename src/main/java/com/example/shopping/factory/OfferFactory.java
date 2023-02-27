package com.example.shopping.factory;

import com.example.shopping.domain.Category;
import com.example.shopping.domain.Coupon;
import com.example.shopping.domain.Offer;
import com.example.shopping.domain.requests.OfferRequest;
import com.example.shopping.dtos.OfferDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OfferFactory {
    public OfferDTO from(Offer offer, Coupon coupon, List<Category> categories) {
        var response = new OfferDTO();

        response.setId(offer.getId());
        response.setTitle(offer.getTitle());
        response.setDescription(offer.getDescription());
        response.setPrice(offer.getPrice());
        response.setActive(offer.getDeletedAt() == null);
        response.setCategories(categories);

        if(coupon != null) {
            response.setCoupon(coupon);
        }

        return response;
    }

    public Offer from(OfferRequest offer, Coupon coupon) {
        var response = new Offer();

        response.setId(offer.getId());
        response.setTitle(offer.getTitle());
        response.setPrice(offer.getPrice());
        response.setDescription(offer.getDescription());
        response.setDeletedAt(offer.getDeletedAt());
        response.setCouponId(coupon == null ? null : coupon.getId());

        return response;
    }
}
