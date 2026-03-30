package com.afpr.pfm.finance.category.application;

import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.category.domain.CategoryMother;
import com.afpr.pfm.finance.category.domain.CategoryRepository;
import com.afpr.pfm.finance.shared.exception.ConflictException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryCreateServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryCreateService underTest;

    @Test
    void create_saveCategory() {
        Category category = CategoryMother.withoutId();
        Category savedCategory = CategoryMother.random();
        when(categoryRepository.existsByName(category.getName())).thenReturn(false);
        when(categoryRepository.save(category)).thenReturn(savedCategory);

        Category result = underTest.create(category);

        assertThat(result).isEqualTo(savedCategory);
        verify(categoryRepository).save(category);
    }

    @Test
    void create_throwsConflictExceptionWhenNameAlreadyExists() {
        Category category = CategoryMother.withoutId();
        when(categoryRepository.existsByName(category.getName())).thenReturn(true);

        assertThatThrownBy(() -> underTest.create(category))
                .isInstanceOf(ConflictException.class)
                .hasMessage("Category with name " + category.getName() + " already exists");

        verify(categoryRepository, never()).save(any());
    }
}
