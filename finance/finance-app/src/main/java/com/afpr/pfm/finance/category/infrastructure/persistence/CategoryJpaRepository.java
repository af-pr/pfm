package com.afpr.pfm.finance.category.infrastructure.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afpr.pfm.finance.category.infrastructure.persistence.entity.CategoryEntity;

interface CategoryJpaRepository extends JpaRepository<CategoryEntity, UUID> {

    boolean existsByName(String name);
}
