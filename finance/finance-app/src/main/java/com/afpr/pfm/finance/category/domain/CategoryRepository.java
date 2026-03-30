package com.afpr.pfm.finance.category.domain;

public interface CategoryRepository {

    Category save(Category category);

    boolean existsByName(String name);
}
