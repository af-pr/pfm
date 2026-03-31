package com.afpr.pfm.finance.step.worlds;

import com.afpr.pfm.finance.client.dto.CategoryResponse;
import com.afpr.pfm.finance.client.dto.PagedCategoryResponse;
import io.cucumber.spring.ScenarioScope;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Component
@ScenarioScope
public class CategoryWorld extends World {

    private String categoryName;
    private UUID categoryId;
    private List<CategoryResponse> createdCategories = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public ResponseEntity<CategoryResponse> getCreateCategoryResponse() {
        return (ResponseEntity<CategoryResponse>) getLastResponse();
    }

    @SuppressWarnings("unchecked")
    public ResponseEntity<CategoryResponse> getCategoryResponse() {
        return (ResponseEntity<CategoryResponse>) getLastResponse();
    }

    @SuppressWarnings("unchecked")
    public ResponseEntity<PagedCategoryResponse> getPagedCategoryResponse() {
        return (ResponseEntity<PagedCategoryResponse>) getLastResponse();
    }

}

