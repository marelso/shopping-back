package com.example.shopping.service;

import static com.googlecode.catchexception.CatchException.catchException;
import com.example.shopping.domain.Coupon;
import com.example.shopping.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class CouponServiceTest {
    @Mock
    private CouponRepository repository;
    @InjectMocks
    private CouponService subject;

    @Test
    public void shouldThrowExceptionWhenThereIsNoCouponWithGivenId() {
        final Integer couponId = Integer.valueOf(1);
        final Optional<Coupon> coupon = Optional.empty();

        given(repository.findById(couponId)).willReturn(coupon);


        catchException(() -> subject.get(couponId));
    }
}