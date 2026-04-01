package com.afpr.pfm.finance.category.infrastructure.http;

import com.afpr.pfm.finance.category.application.CategoryCreateService;
import com.afpr.pfm.finance.category.application.CategoryFinderService;
import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.category.domain.CategoryMother;
import com.afpr.pfm.finance.category.infrastructure.http.mapper.CategoryControllerMapper;
import com.afpr.pfm.finance.client.dto.CategoryCreateRequestDto;
import com.afpr.pfm.finance.client.dto.CategoryResponseDto;
import com.afpr.pfm.finance.client.dto.PagedCategoryResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryCreateService categoryCreateService;

    @Mock
    private CategoryFinderService categoryFinderService;

    @Mock
    private CategoryControllerMapper mapper;

    @InjectMocks
    private CategoryController sut;

    @Test
    void createCategory() {
        Category category = CategoryMother.withoutId();
        CategoryCreateRequestDto request = CategoryCreateRequestDto.builder().name(category.getName()).build();
        Category createdCategory = CategoryMother.random();
        CategoryResponseDto response = CategoryResponseDto.builder().id(createdCategory.getId()).name(createdCategory.getName()).build();
        when(mapper.toDomain(request)).thenReturn(category);
        when(categoryCreateService.create(category)).thenReturn(createdCategory);
        when(mapper.toResponse(createdCategory)).thenReturn(response);

        ResponseEntity<CategoryResponseDto> result = sut.createCategory(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isSameAs(response);
        verify(mapper).toDomain(request);
        verify(categoryCreateService).create(category);
        verify(mapper).toResponse(createdCategory);
    }

    @Test
    void getCategoryById_categoryFound() {
        Category category = CategoryMother.random();
        CategoryResponseDto response = CategoryResponseDto.builder().id(category.getId()).name(category.getName()).build();
        when(categoryFinderService.findById(category.getId())).thenReturn(Optional.of(category));
        when(mapper.toResponse(category)).thenReturn(response);

        ResponseEntity<CategoryResponseDto> result = sut.getCategoryById(category.getId());

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isSameAs(response);
    }

    @Test
    void getCategoryById_notFound() {
        UUID id = UUID.randomUUID();
        when(categoryFinderService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<CategoryResponseDto> result = sut.getCategoryById(id);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody()).isNull();
    }

    @Test
    void getCategories_returnsPagedResponse() {
        var pageable = PageRequest.of(0, 10);
        var categories = List.of(CategoryMother.random(), CategoryMother.random());
        var page = new PageImpl<Category>(categories, pageable, categories.size());
        var pagedResponse = PagedCategoryResponseDto.builder()
                .content(List.of())
                .totalElements((long) categories.size())
                .totalPages(1)
                .pageNumber(0)
                .pageSize(10)
                .build();
        when(categoryFinderService.findAll(pageable)).thenReturn(page);
        when(mapper.toPagedResponse(page)).thenReturn(pagedResponse);

        ResponseEntity<PagedCategoryResponseDto> result = sut.getCategories(0, 10);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isSameAs(pagedResponse);
        verify(categoryFinderService).findAll(pageable);
        verify(mapper).toPagedResponse(page);
    }

    @Test
    void getCategories_emptyPage() {
        var pageable = PageRequest.of(0, 10);
        var page = new PageImpl<Category>(List.of(), pageable, 0);
        var pagedResponse = PagedCategoryResponseDto.builder()
                .content(List.of())
                .totalElements(0L)
                .totalPages(0)
                .pageNumber(0)
                .pageSize(10)
                .build();
        when(categoryFinderService.findAll(pageable)).thenReturn(page);
        when(mapper.toPagedResponse(page)).thenReturn(pagedResponse);

        ResponseEntity<PagedCategoryResponseDto> result = sut.getCategories(0, 10);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isSameAs(pagedResponse);
        verify(categoryFinderService).findAll(pageable);
        verify(mapper).toPagedResponse(page);
    }
}

