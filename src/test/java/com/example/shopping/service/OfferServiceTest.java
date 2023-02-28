package com.example.shopping.service;

import com.example.shopping.domain.Category;
import com.example.shopping.domain.Offer;
import com.example.shopping.exception.NotFoundException;
import com.example.shopping.repository.OfferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class OfferServiceTest {
    @Mock
    private OfferRepository repository;
    @InjectMocks
    private OfferService subject;

    @Test
    public void shouldThrowExceptionWhenThereIsNoOfferWithGivenId() {
        final Integer offerId = 1;
        final Optional<Offer> offer = Optional.empty();

        given(repository.findById(offerId)).willReturn(offer);


        catchException(() -> subject.findById(offerId));


        assertThat(caughtException(), instanceOf(NotFoundException.class));
        then(repository).should().findById(offerId);
    }
}