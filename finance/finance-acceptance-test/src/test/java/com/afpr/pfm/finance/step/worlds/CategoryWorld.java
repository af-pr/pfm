package com.afpr.pfm.finance.step.worlds;

import com.afpr.pfm.finance.client.dto.CategoryResponse;
import io.cucumber.spring.ScenarioScope;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ScenarioScope
public class CategoryWorld extends World {

    private String categoryName;

    @SuppressWarnings("unchecked")
    public ResponseEntity<CategoryResponse> getCreateCategoryResponse() {
        return (ResponseEntity<CategoryResponse>) getLastResponse();
    }

}
