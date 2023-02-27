package com.example.shopping.service;

import static com.googlecode.catchexception.CatchException.catchException;
import com.example.shopping.domain.Coupon;
import com.example.shopping.exception.NotFoundException;
import com.example.shopping.factory.CouponFactory;
import com.example.shopping.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
class CouponServiceTest {
    @Mock
    private CouponRepository repository;
    @Mock
    private CouponFactory factory;
    @InjectMocks
    private CouponService subject;

    @Test
    public void shouldThrowExceptionWhenThereIsNoCouponWithGivenId() {
        final Integer couponId = 1;
        final Optional<Coupon> coupon = Optional.empty();

        given(repository.findById(couponId)).willReturn(coupon);


        catchException(() -> subject.findById(couponId));


        assertThat(caughtException(), instanceOf(NotFoundException.class));
        then(repository).should().findById(couponId);
    }

    @Test
    public void shouldInsertNewCouponInRepository() {
        var expectedCoupon = mock(Coupon.class);
        var request = mock(Coupon.class);

        given(repository.save(request)).willReturn(expectedCoupon);


        var result = subject.create(request);


        assertEquals(expectedCoupon, result);
    }

    @Test
    public void shouldRetrieveAllCoupons() {
        var firstCoupon = mock(Coupon.class);
        var secondCoupon = mock(Coupon.class);
        var list = Arrays.asList(firstCoupon, secondCoupon);

        given(repository.findAll()).willReturn(list);


        var result = subject.list();


        assertEquals(list, result);
        then(repository).should().findAll();
    }

    @Test
    public void shouldUpdateGivenCoupon() {
        var couponId = 1;
        var request = mock(Coupon.class);
        var existing = mock(Coupon.class);

        given(factory.from(existing, request)).willReturn(existing);
        given(repository.findById(couponId)).willReturn(Optional.of(existing));
        given(repository.save(any(Coupon.class))).willReturn(existing);


        var result = subject.update(couponId, request);


        then(factory).should().from(existing, request);
        then(repository).should().save(existing);
        assertEquals(existing, result);
    }
}