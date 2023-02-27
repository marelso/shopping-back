package com.example.shopping.service;

import com.example.shopping.domain.Catalog;
import com.example.shopping.repository.CatalogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.googlecode.catchexception.CatchException.catchException;
import static org.mockito.BDDMockito.given;

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
    }
}