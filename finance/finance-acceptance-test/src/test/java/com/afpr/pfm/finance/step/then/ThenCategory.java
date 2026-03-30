package com.afpr.pfm.finance.step.then;

import com.afpr.pfm.finance.step.worlds.CategoryWorld;

import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ThenCategory {

    private final CategoryWorld world;

    @Then("the category is created")
    public void theCategoryIsCreated() {
        var response = world.getCreateCategoryResponse();

        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(world.getCategoryName());
    }

    @Then("the category is not created for duplicated name")
    public void theCategoryIsNotCreatedForDuplicatedName() {
        var response = world.getCreateCategoryResponse();

        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.CONFLICT.value());
    }
}
