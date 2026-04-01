package com.afpr.pfm.finance.step.worlds;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.afpr.pfm.finance.category.domain.Category;
import com.afpr.pfm.finance.client.dto.CategoryResponseDto;
import com.afpr.pfm.finance.client.dto.PagedCategoryResponseDto;

import io.cucumber.spring.ScenarioScope;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ScenarioScope
public class CategoryWorld extends World {

    private Category category;
    private UUID categoryId;
    private List<CategoryResponseDto> createdCategories = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public ResponseEntity<CategoryResponseDto> getCreateCategoryResponse() {
        return (ResponseEntity<CategoryResponseDto>) getLastResponse();
    }

    @SuppressWarnings("unchecked")
    public ResponseEntity<CategoryResponseDto> getCategoryResponse() {
        return (ResponseEntity<CategoryResponseDto>) getLastResponse();
    }

    @SuppressWarnings("unchecked")
    public ResponseEntity<PagedCategoryResponseDto> getPagedCategoryResponse() {
        return (ResponseEntity<PagedCategoryResponseDto>) getLastResponse();
    }

}

