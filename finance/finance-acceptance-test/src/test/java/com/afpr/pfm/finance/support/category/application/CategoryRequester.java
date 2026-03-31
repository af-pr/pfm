package com.afpr.pfm.finance.support.category.application;

import com.afpr.pfm.finance.client.api.CategoryApi;
import com.afpr.pfm.finance.client.dto.CategoryCreateRequest;
import com.afpr.pfm.finance.client.dto.CategoryResponse;
import com.afpr.pfm.finance.client.dto.PagedCategoryResponse;
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

    public ResponseEntity<CategoryResponse> create(String name) {
        try {
            CategoryCreateRequest request = CategoryCreateRequest.builder()
                    .name(name)
                    .build();

            return categoryApi.createCategory(request);
        } catch (FeignException e) {
            return ResponseEntity.status(e.status()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<CategoryResponse> getById(UUID id) {
        try {
            return categoryApi.getCategoryById(id);
        } catch (FeignException e) {
            return ResponseEntity.status(e.status()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<PagedCategoryResponse> getAll(int page, int size) {
        try {
            return categoryApi.getCategories(page, size);
        } catch (FeignException e) {
            return ResponseEntity.status(e.status()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

