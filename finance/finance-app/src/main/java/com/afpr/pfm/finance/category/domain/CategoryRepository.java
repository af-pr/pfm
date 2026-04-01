package com.afpr.pfm.finance.category.domain;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryRepository {

    Category save(Category category);

    boolean existsByName(String name);

    Optional<Category> findById(UUID id);

    Page<Category> findAll(Pageable pageable);
}
