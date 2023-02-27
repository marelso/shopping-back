package com.example.shopping.controller;

import com.example.shopping.domain.Coupon;
import com.example.shopping.service.CouponService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "List all existing coupons.")
    public List<Coupon> get() {
        return service.list();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lists the requested coupon if it exists.")
    public Coupon get(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new coupon", notes = "Coupons can be referenced by offers.")
    public Coupon post(@RequestBody Coupon request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update an existing coupon")
    public Coupon put(
            @PathVariable Integer id,
            @RequestBody Coupon request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete an existing coupon")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
