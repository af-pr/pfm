package com.afpr.pfm.finance.category.application;

import com.afpr.pfm.finance.category.domain.CategoryMother;
import com.afpr.pfm.finance.category.domain.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryUpdaterServiceTest {
    @Mock
    CategoryRepository categoryRepository;
    @InjectMocks
    CategoryUpdaterService updaterService;

    @Test
    void update_callsRepositoryAndReturnsUpdatedCategory() {
        var category = CategoryMother.random();
        var updatedCategory = category.toBuilder().name("updated").build();
        when(categoryRepository.save(category)).thenReturn(updatedCategory);

        var result = updaterService.update(category);

        assertThat(result.getName()).isEqualTo(updatedCategory.getName());
        verify(categoryRepository).save(category);
    }
}
