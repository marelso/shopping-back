package com.example.shopping.service;

import com.example.shopping.domain.Catalog;
import com.example.shopping.exception.NotFoundException;
import com.example.shopping.factory.CatalogFactory;
import com.example.shopping.repository.CatalogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CatalogServiceTest {
    @Mock
    private CatalogRepository repository;
    @Mock
    private CatalogFactory factory;
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

    @Test
    public void shouldInsertNewCatalogInRepository() {
        var expectedCatalog = mock(Catalog.class);
        var request = mock(Catalog.class);

        given(repository.save(request)).willReturn(expectedCatalog);


        var result = subject.create(request);


        assertEquals(expectedCatalog, result);
    }

    @Test
    public void shouldRetrieveAllCatalogs() {
        var firstCatalog = mock(Catalog.class);
        var secondCatalog = mock(Catalog.class);
        var list = Arrays.asList(firstCatalog, secondCatalog);

        given(repository.findAll()).willReturn(list);


        var result = subject.list();


        assertEquals(list, result);
        then(repository).should().findAll();
    }

    @Test
    public void shouldUpdateGivenCatalog() {
        var catalogId = 1;
        var request = mock(Catalog.class);
        var existing = mock(Catalog.class);

        given(factory.from(existing, request)).willReturn(existing);
        given(repository.findById(catalogId)).willReturn(Optional.of(existing));
        given(repository.save(any(Catalog.class))).willReturn(existing);


        var result = subject.update(catalogId, request);


        then(factory).should().from(existing, request);
        then(repository).should().save(existing);
        assertEquals(existing, result);
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingInvalidCatalog() {
        var catalogId = 1;
        var request = mock(Catalog.class);

        given(repository.findById(catalogId)).willReturn(Optional.empty());


        catchException(() -> subject.update(catalogId, request));


        assertThat(caughtException(), instanceOf(NotFoundException.class));
        then(repository).should().findById(catalogId);
    }
}