package com.afpr.pfm.finance.category.infrastructure.http;

import com.afpr.pfm.finance.category.application.CategoryCreateService;
import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.category.infrastructure.http.mapper.CategoryControllerMapper;
import com.afpr.pfm.finance.category.domain.CategoryMother;
import com.afpr.pfm.finance.client.dto.CategoryCreateRequest;
import com.afpr.pfm.finance.client.dto.CategoryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryCreateService categoryCreateService;

    @Mock
    private CategoryControllerMapper mapper;

    @InjectMocks
    private CategoryController underTest;

    @Test
    void createCategory() {
        Category category = CategoryMother.withoutId();
        CategoryCreateRequest request = CategoryCreateRequest.builder().name(category.getName()).build();
        Category createdCategory = CategoryMother.random();
        CategoryResponse response = CategoryResponse.builder().id(createdCategory.getId()).name(createdCategory.getName()).build();
        when(mapper.toDomain(request)).thenReturn(category);
        when(categoryCreateService.create(category)).thenReturn(createdCategory);
        when(mapper.toResponse(createdCategory)).thenReturn(response);

        ResponseEntity<CategoryResponse> result = underTest.createCategory(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isSameAs(response);
        verify(mapper).toDomain(request);
        verify(categoryCreateService).create(category);
        verify(mapper).toResponse(createdCategory);
    }
}

