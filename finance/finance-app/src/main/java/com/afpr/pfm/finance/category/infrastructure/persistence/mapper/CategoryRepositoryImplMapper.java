package com.afpr.pfm.finance.category.infrastructure.persistence.mapper;

import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.category.infrastructure.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryRepositoryImplMapper {

    CategoryEntity toEntity(Category category);

    Category toDomain(CategoryEntity entity);
}
