package com.afpr.pfm.finance.category.infrastructure.persistence;

import com.afpr.pfm.finance.category.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface CategoryJpaRepository extends JpaRepository<CategoryEntity, UUID> {

    boolean existsByName(String name);
}
