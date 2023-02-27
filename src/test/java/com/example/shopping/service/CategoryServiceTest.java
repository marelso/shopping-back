package com.example.shopping.service;

import com.example.shopping.domain.Category;
import com.example.shopping.exception.NotFoundException;
import com.example.shopping.repository.CategoryRepository;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryRepository repository;
    @InjectMocks
    private CategoryService subject;

    @Test
    public void shouldThrowExceptionWhenThereIsNoCategoryWithGivenId() {
        final Integer categoryId = 1;
        final Optional<Category> category = Optional.empty();

        given(repository.findById(categoryId)).willReturn(category);


        catchException(() -> subject.findById(categoryId));


        assertThat(caughtException(), instanceOf(NotFoundException.class));
        then(repository).should().findById(categoryId);
    }

    @Test
    public void shouldRetrieveGivenCategoryById() {
        var categoryId = 0;
        var expectedCategory = mock(Category.class);

        given(repository.findById(categoryId)).willReturn(Optional.of(expectedCategory));


        var result = subject.findById(categoryId);


        then(repository).should().findById(categoryId);
        assertEquals(expectedCategory, result);
    }

    @Test
    public void shouldRetrieveAllCategories() {
        var firstCategory = mock(Category.class);
        var secondCategory = mock(Category.class);
        var list = Arrays.asList(firstCategory, secondCategory);

        given(repository.findAll()).willReturn(list);


        var result = subject.list();
    }

}