package com.example.shopping.controller;

import com.example.shopping.domain.Coupon;
import com.example.shopping.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService service;

    @GetMapping
    public List<Coupon> get() {
        return service.list();
    }

    @GetMapping("/{id}")
    public Coupon get(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Coupon post(@RequestBody Coupon request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public Coupon put(
            @PathVariable Integer id,
            @RequestBody Coupon request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
