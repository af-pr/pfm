package com.afpr.pfm.finance.category.infrastructure.persistence;

import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.category.domain.CategoryMother;
import com.afpr.pfm.finance.category.infrastructure.persistence.entity.CategoryEntity;
import com.afpr.pfm.finance.category.infrastructure.persistence.mapper.CategoryRepositoryImplMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
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
    private CategoryRepositoryImpl sut;

    @Test
    void save_mapsToEntityPersistsAndMapsToDomain() {
        Category category = CategoryMother.withoutId();
        CategoryEntity entity = CategoryEntity.builder().name(category.getName()).build();
        CategoryEntity savedEntity = entity.toBuilder().id(UUID.randomUUID()).build();
        Category savedCategory = CategoryMother.random();
        when(mapper.toEntity(category)).thenReturn(entity);
        when(jpaRepository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDomain(savedEntity)).thenReturn(savedCategory);

        Category result = sut.save(category);

        assertThat(result).isEqualTo(savedCategory);
        verify(mapper).toEntity(category);
        verify(jpaRepository).save(entity);
        verify(mapper).toDomain(savedEntity);
    }

    @Test
    void existsByName_nameExists() {
        String categoryName = CategoryMother.random().getName();
        when(jpaRepository.existsByName(categoryName)).thenReturn(true);

        boolean result = sut.existsByName(categoryName);

        assertThat(result).isTrue();
        verify(jpaRepository).existsByName(categoryName);
    }

    @Test
    void existsByName_nameDoesNotExist() {
        String categoryName = CategoryMother.random().getName();
        when(jpaRepository.existsByName(categoryName)).thenReturn(false);

        boolean result = sut.existsByName(categoryName);

        assertThat(result).isFalse();
        verify(jpaRepository).existsByName(categoryName);
    }

    @Test
    void findById_categoryFound() {
        Category category = CategoryMother.random();
        CategoryEntity entity = CategoryEntity.builder().id(category.getId()).name(category.getName()).build();
        when(jpaRepository.findById(category.getId())).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(category);

        Optional<Category> result = sut.findById(category.getId());

        assertThat(result).contains(category);
        verify(jpaRepository).findById(category.getId());
        verify(mapper).toDomain(entity);
    }

    @Test
    void findById_categoryNotFound() {
        UUID id = UUID.randomUUID();
        when(jpaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Category> result = sut.findById(id);

        assertThat(result).isEmpty();
        verify(jpaRepository).findById(id);
    }

    @Test
    void findAll_returnsMappedPage() {
        var pageable = PageRequest.of(0, 5);
        Category category = CategoryMother.random();
        CategoryEntity entity = CategoryEntity.builder().id(category.getId()).name(category.getName()).build();
        Page<CategoryEntity> entityPage = new PageImpl<CategoryEntity>(List.of(entity), pageable, 1L);
        when(jpaRepository.findAll(pageable)).thenReturn(entityPage);
        when(mapper.toDomain(entity)).thenReturn(category);

        Page<Category> result = sut.findAll(pageable);

        assertThat(result.getContent()).containsExactly(category);
        assertThat(result.getTotalElements()).isEqualTo(1L);
        verify(jpaRepository).findAll(pageable);
    }
}
