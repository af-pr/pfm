package com.afpr.pfm.finance.category.application;

import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.category.domain.CategoryRepository;
import com.afpr.pfm.finance.shared.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryCreateService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Category create(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new ConflictException("Category with name " + category.getName() + " already exists");
        }
        return categoryRepository.save(category);
    }
}
