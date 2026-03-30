package com.afpr.pfm.finance.category.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.category.domain.CategoryRepository;
import com.afpr.pfm.finance.category.infrastructure.persistence.mapper.CategoryRepositoryImplMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJpaRepository jpaRepository;
    private final CategoryRepositoryImplMapper mapper;

    @Override
    public Category save(Category category) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(category)));
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }
}
