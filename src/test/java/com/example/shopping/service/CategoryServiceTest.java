package com.example.shopping.service;

import com.example.shopping.domain.Category;
import com.example.shopping.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.googlecode.catchexception.CatchException.catchException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

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
    }

}