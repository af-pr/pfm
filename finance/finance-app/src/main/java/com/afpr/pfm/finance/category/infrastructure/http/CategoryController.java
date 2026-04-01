package com.afpr.pfm.finance.category.infrastructure.http;

import com.afpr.pfm.finance.category.application.CategoryCreateService;
import com.afpr.pfm.finance.category.application.CategoryFinderService;
import com.afpr.pfm.finance.category.infrastructure.http.mapper.CategoryControllerMapper;
import com.afpr.pfm.finance.client.api.CategoryApi;
import com.afpr.pfm.finance.client.dto.CategoryCreateRequest;
import com.afpr.pfm.finance.client.dto.CategoryResponse;
import com.afpr.pfm.finance.client.dto.PagedCategoryResponse;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoryApi {

    private final CategoryCreateService categoryCreateService;
    private final CategoryFinderService categoryFinderService;
    private final CategoryControllerMapper mapper;

    @Override
    public ResponseEntity<CategoryResponse> createCategory(final CategoryCreateRequest categoryCreateRequest) {
        var category = mapper.toDomain(categoryCreateRequest);
        var createdCategory = categoryCreateService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(createdCategory));
    }

    @Override
    public ResponseEntity<PagedCategoryResponse> getCategories(final Integer page, final Integer size) {
        return ResponseEntity.ok(mapper.toPagedResponse(categoryFinderService.findAll(PageRequest.of(page, size))));
    }

    @Override
    public ResponseEntity<CategoryResponse> getCategoryById(final UUID id) {
        return categoryFinderService.findById(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
