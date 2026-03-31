package com.afpr.pfm.finance.category.infrastructure.http;

import com.afpr.pfm.finance.category.application.CategoryCreateService;
import com.afpr.pfm.finance.category.infrastructure.http.mapper.CategoryControllerMapper;
import com.afpr.pfm.finance.client.api.CategoryApi;
import com.afpr.pfm.finance.client.dto.CategoryCreateRequest;
import com.afpr.pfm.finance.client.dto.CategoryResponse;
import com.afpr.pfm.finance.client.dto.PagedCategoryResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoryApi {

    private final CategoryCreateService categoryCreateService;
    private final CategoryControllerMapper mapper;

    @Override
    public ResponseEntity<CategoryResponse> createCategory(CategoryCreateRequest categoryCreateRequest) {
        var category = mapper.toDomain(categoryCreateRequest);
        var createdCategory = categoryCreateService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(createdCategory));
    }

    @Override
    public ResponseEntity<PagedCategoryResponse> getCategories(@Min(0) @Valid Integer page,
            @Min(1) @Max(100) @Valid Integer size) {
        throw new UnsupportedOperationException("Unimplemented method 'getCategories'");
    }

    @Override
    public ResponseEntity<CategoryResponse> getCategoryById(UUID id) {
        throw new UnsupportedOperationException("Unimplemented method 'getCategoryById'");
    }
}
