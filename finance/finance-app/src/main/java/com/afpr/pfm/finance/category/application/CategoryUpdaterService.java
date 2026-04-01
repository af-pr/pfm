package com.afpr.pfm.finance.category.application;

import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.category.domain.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryUpdaterService {
    private final CategoryRepository categoryRepository;

    public Category update(Category category) {
        return categoryRepository.save(category);
    }
}
