package com.afpr.pfm.finance.support.category.application;

import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.client.api.CategoryApi;
import com.afpr.pfm.finance.client.dto.CategoryCreateRequestDto;
import com.afpr.pfm.finance.client.dto.CategoryEditionRequestDto;
import com.afpr.pfm.finance.client.dto.CategoryResponseDto;
import com.afpr.pfm.finance.client.dto.PagedCategoryResponseDto;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CategoryRequester {

    private final CategoryApi categoryApi;

    public CategoryRequester(CategoryApi categoryApi) {
        this.categoryApi = categoryApi;
    }

    public ResponseEntity<CategoryResponseDto> create(Category category) {
        var request = CategoryCreateRequestDto.builder()
                        .name(category.getName())
                        .build();
        try {
            return categoryApi.createCategory(request);
        } catch (FeignException e) {
            return ResponseEntity.status(e.status()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<CategoryResponseDto> getById(UUID id) {
        try {
            return categoryApi.getCategoryById(id);
        } catch (FeignException e) {
            return ResponseEntity.status(e.status()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<PagedCategoryResponseDto> getAll(int page, int size) {
        try {
            return categoryApi.getCategories(page, size);
        } catch (FeignException e) {
            return ResponseEntity.status(e.status()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<CategoryResponseDto> update(Category category) {
        var request = CategoryEditionRequestDto.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build();
        try {
            return categoryApi.updateCategory(request.getId(), request);
        } catch (FeignException e) {
            return ResponseEntity.status(e.status()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

