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
        assertThat(response.getBody().getName()).isEqualTo(world.getCategory().getName());
    }

    @Then("the category is not created for duplicated name")
    public void theCategoryIsNotCreatedForDuplicatedName() {
        var response = world.getCreateCategoryResponse();

        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.CONFLICT.value());
    }

    @Then("the category is retrieved successfully")
    public void theCategoryIsRetrievedSuccessfully() {
        var response = world.getCategoryResponse();

        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(world.getCategoryId());
    }

    @Then("the category list is retrieved successfully")
    public void theCategoryListIsRetrievedSuccessfully() {
        var response = world.getPagedCategoryResponse();
        var createdCategories = world.getCreatedCategories();

        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getBody()).isNotNull();

        var responseCategories = response.getBody().getContent();
        assertThat(responseCategories).hasSize(createdCategories.size());
        assertThat(responseCategories).allSatisfy(responseCategory ->
                assertThat(createdCategories).anySatisfy(createdCategory -> {
                    assertThat(responseCategory.getId()).isEqualTo(createdCategory.getId());
                    assertThat(responseCategory.getName()).isEqualTo(createdCategory.getName());
                })
        );
    }

    @Then("the category is edited")
    public void theCategoryIsEdited() {
        var response = world.getCategoryResponse();

        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(world.getCategoryId());
        assertThat(response.getBody().getName()).isEqualTo(world.getCategory().getName());
    }
}
