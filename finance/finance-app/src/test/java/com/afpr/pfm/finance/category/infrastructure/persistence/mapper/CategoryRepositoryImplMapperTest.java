package com.afpr.pfm.finance.category.infrastructure.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.category.domain.CategoryMother;
import com.afpr.pfm.finance.category.infrastructure.persistence.entity.CategoryEntity;

class CategoryRepositoryImplMapperTest {
    private final CategoryRepositoryImplMapper mapper = Mappers.getMapper(CategoryRepositoryImplMapper.class);

    @Test
    void toEntity_CategoryToEntity() {
        Category domain = CategoryMother.random();

        CategoryEntity entity = mapper.toEntity(domain);

        assertThat(entity.getId()).isEqualTo(domain.getId());
        assertThat(entity.getName()).isEqualTo(domain.getName());
    }

    @Test
    void toDomain_EntityToCategory() {
        Category domain = CategoryMother.random();
        CategoryEntity entity = CategoryEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .build();

        Category result = mapper.toDomain(entity);

        assertThat(result.getId()).isEqualTo(domain.getId());
        assertThat(result.getName()).isEqualTo(domain.getName());
    }
}
