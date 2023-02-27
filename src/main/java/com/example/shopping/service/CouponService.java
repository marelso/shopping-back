package com.example.shopping.service;

import com.example.shopping.domain.Coupon;
import com.example.shopping.exception.NotFoundException;
import com.example.shopping.factory.CouponFactory;
import com.example.shopping.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository repository;
    private final CouponFactory factory;

    public Coupon findById(Integer id) {
        return get(id);
    }

    private Coupon get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Coupon", id));
    }

    public Coupon create(Coupon request) {
        return repository.save(request);
    }

    public List<Coupon> list() {
        return repository.findAll();
    }

    public Coupon update(Integer id, Coupon request) {
        var coupon = get(id);

        return repository.save(factory.from(coupon, request));
    }
}
