package com.afpr.pfm.finance.category.infrastructure.http;

import com.afpr.pfm.finance.category.application.CategoryCreateService;
import com.afpr.pfm.finance.category.application.CategoryFinderService;
import com.afpr.pfm.finance.category.infrastructure.http.mapper.CategoryControllerMapper;
import com.afpr.pfm.finance.category.application.CategoryUpdaterService;
import com.afpr.pfm.finance.client.api.CategoryApi;
import com.afpr.pfm.finance.client.dto.CategoryCreateRequestDto;
import com.afpr.pfm.finance.client.dto.CategoryEditionRequestDto;
import com.afpr.pfm.finance.client.dto.CategoryResponseDto;
import com.afpr.pfm.finance.client.dto.PagedCategoryResponseDto;
import com.afpr.pfm.finance.shared.exception.NotValidException;

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
    private final CategoryUpdaterService categoryUpdaterService;
    private final CategoryControllerMapper mapper;

    @Override
    public ResponseEntity<CategoryResponseDto> createCategory(final CategoryCreateRequestDto categoryCreateRequest) {
        var category = mapper.toDomain(categoryCreateRequest);
        var createdCategory = categoryCreateService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(createdCategory));
    }

    @Override
    public ResponseEntity<PagedCategoryResponseDto> getCategories(final Integer page, final Integer size) {
        return ResponseEntity.ok(mapper.toPagedResponse(categoryFinderService.findAll(PageRequest.of(page, size))));
    }

    @Override
    public ResponseEntity<CategoryResponseDto> getCategoryById(final UUID id) {
        return categoryFinderService.findById(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<CategoryResponseDto> updateCategory(final UUID id, final CategoryEditionRequestDto categoryEditionRequestDto) {
        if (!id.equals(categoryEditionRequestDto.getId())) {
            throw new NotValidException("Path id and body id must match");
        }
        var category = mapper.toDomain(categoryEditionRequestDto);
        var updated = categoryUpdaterService.update(category);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }
}
