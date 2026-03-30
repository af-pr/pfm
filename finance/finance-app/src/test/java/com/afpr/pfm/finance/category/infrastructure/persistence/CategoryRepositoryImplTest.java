package com.afpr.pfm.finance.category.infrastructure.persistence;

import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.category.infrastructure.persistence.entity.CategoryEntity;
import com.afpr.pfm.finance.category.infrastructure.persistence.mapper.CategoryRepositoryImplMapper;
import com.afpr.pfm.finance.category.domain.CategoryMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryRepositoryImplTest {

    @Mock
    private CategoryJpaRepository jpaRepository;

    @Mock
    private CategoryRepositoryImplMapper mapper;

    @InjectMocks
    private CategoryRepositoryImpl underTest;

    @Test
    void save_mapsToEntityPersistsAndMapsToDomain() {
        Category category = CategoryMother.withoutId();
        CategoryEntity entity = CategoryEntity.builder().name(category.getName()).build();
        CategoryEntity savedEntity = entity.toBuilder().id(UUID.randomUUID()).build();
        Category savedCategory = CategoryMother.random();
        when(mapper.toEntity(category)).thenReturn(entity);
        when(jpaRepository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDomain(savedEntity)).thenReturn(savedCategory);

        Category result = underTest.save(category);

        assertThat(result).isEqualTo(savedCategory);
        verify(mapper).toEntity(category);
        verify(jpaRepository).save(entity);
        verify(mapper).toDomain(savedEntity);
    }

    @Test
    void existsByName_returnsTrueWhenNameExists() {
        String categoryName = CategoryMother.random().getName();

        when(jpaRepository.existsByName(categoryName)).thenReturn(true);

        boolean result = underTest.existsByName(categoryName);

        assertThat(result).isTrue();
        verify(jpaRepository).existsByName(categoryName);
    }

    @Test
    void existsByName_returnsFalseWhenNameDoesNotExist() {
        String categoryName = CategoryMother.random().getName();

        when(jpaRepository.existsByName(categoryName)).thenReturn(false);

        boolean result = underTest.existsByName(categoryName);

        assertThat(result).isFalse();
        verify(jpaRepository).existsByName(categoryName);
    }
}
