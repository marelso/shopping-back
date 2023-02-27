package com.example.shopping.factory;

import com.example.shopping.domain.Coupon;
import org.springframework.stereotype.Component;

@Component
public class CouponFactory {
    public Coupon from(Coupon coupon, Coupon request) {
        if(request.getCode() != null) {
            coupon.setCode(request.getCode());
        }

        return coupon;
    }
}
