package com.example.shopping.service;

import com.example.shopping.domain.Coupon;
import com.example.shopping.exception.NotFoundException;
import com.example.shopping.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository repository;
    public Coupon findById(Integer id) {
        return get(id);
    }

    private Coupon get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Coupon", id));
    }
}
