package com.afpr.pfm.finance.category.infrastructure.http.mapper;

import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.client.dto.CategoryCreateRequestDto;
import com.afpr.pfm.finance.client.dto.CategoryEditionRequestDto;
import com.afpr.pfm.finance.client.dto.CategoryResponseDto;
import com.afpr.pfm.finance.client.dto.PagedCategoryResponseDto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface CategoryControllerMapper {

    Category toDomain(CategoryCreateRequestDto request);
    
    Category toDomain(CategoryEditionRequestDto request);

    CategoryResponseDto toResponse(Category category);

    default PagedCategoryResponseDto toPagedResponse(Page<Category> page) {
        return PagedCategoryResponseDto.builder()
                .content(page.getContent().stream().map(this::toResponse).toList())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .build();
    }
}
