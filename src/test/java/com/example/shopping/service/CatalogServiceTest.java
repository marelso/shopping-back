package com.example.shopping.service;

import com.example.shopping.domain.Catalog;
import com.example.shopping.exception.NotFoundException;
import com.example.shopping.repository.CatalogRepository;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CatalogServiceTest {
    @Mock
    private CatalogRepository repository;
    @InjectMocks
    private CatalogService subject;

    @Test
    public void shouldThrowExceptionWhenThereIsNoCatalogWithGivenId() {
        final Integer catalogId = 1;
        final Optional<Catalog> catalog = Optional.empty();

        given(repository.findById(catalogId)).willReturn(catalog);


        catchException(() -> subject.findById(catalogId));


        assertThat(caughtException(), instanceOf(NotFoundException.class));
        then(repository).should().findById(catalogId);
    }
}