package com.afpr.pfm.finance.category.application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.category.domain.CategoryMother;
import com.afpr.pfm.finance.category.domain.CategoryRepository;

@ExtendWith(MockitoExtension.class)
class CategoryFinderServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryFinderService underTest;

    @Test
    void findById_returnsCategory() {
        Category category = CategoryMother.random();
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));

        Optional<Category> result = underTest.findById(category.getId());

        assertThat(result).contains(category);
    }

    @Test
    void findById_returnsEmpty() {
        UUID id = UUID.randomUUID();
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Category> result = underTest.findById(id);

        assertThat(result).isEmpty();
    }

    @Test
    void findAll_returnsPagedCategories() {
        PageRequest pageable = PageRequest.of(0, 2);
        List<Category> categories = List.of(CategoryMother.random(), CategoryMother.random());
        Page<Category> page = new PageImpl<>(categories, pageable, categories.size());
        when(categoryRepository.findAll(pageable)).thenReturn(page);

        Page<Category> result = underTest.findAll(pageable);

        assertThat(result.getContent()).containsExactlyElementsOf(categories);
    }
}
