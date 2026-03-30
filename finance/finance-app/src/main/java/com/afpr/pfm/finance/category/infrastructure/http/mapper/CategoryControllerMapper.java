package com.afpr.pfm.finance.category.infrastructure.http.mapper;

import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.client.dto.CategoryCreateRequest;
import com.afpr.pfm.finance.client.dto.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryControllerMapper {

    Category toDomain(CategoryCreateRequest request);

    CategoryResponse toResponse(Category category);
}
