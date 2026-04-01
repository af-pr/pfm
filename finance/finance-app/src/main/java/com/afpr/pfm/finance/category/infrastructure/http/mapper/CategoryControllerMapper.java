package com.afpr.pfm.finance.category.infrastructure.http.mapper;

import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.client.dto.CategoryCreateRequest;
import com.afpr.pfm.finance.client.dto.CategoryResponse;
import com.afpr.pfm.finance.client.dto.PagedCategoryResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface CategoryControllerMapper {

    Category toDomain(CategoryCreateRequest request);

    CategoryResponse toResponse(Category category);

    default PagedCategoryResponse toPagedResponse(Page<Category> page) {
        return PagedCategoryResponse.builder()
                .content(page.getContent().stream().map(this::toResponse).toList())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .build();
    }
}
