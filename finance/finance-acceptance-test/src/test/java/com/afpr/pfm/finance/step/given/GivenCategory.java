package com.afpr.pfm.finance.step.given;

import java.util.stream.IntStream;

import com.afpr.pfm.finance.step.worlds.CategoryWorld;
import com.afpr.pfm.finance.support.category.application.CategoryMother;
import com.afpr.pfm.finance.support.category.application.CategoryRequester;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;

@AllArgsConstructor
public class GivenCategory {

    private final CategoryWorld categoryWorld;
    private final CategoryRequester categoryRequester;

    @Given("a valid category is declared")
    public void aValidCategoryIsDeclared() {
        categoryWorld.setCategory(CategoryMother.newCategory());
    }

    @Given("a category is created")
    public void aCategoryIsCreated() {
        aValidCategoryIsDeclared();
        var response = categoryRequester.create(categoryWorld.getCategory());
        categoryWorld.setLastResponse(response);
        categoryWorld.setCategoryId(response.getBody().getId());
    }

    @And("another category with the same name")
    public void anotherCategoryWithTheSameName() {
        // No implementation needed: the category name is already stored in categoryWorld
    }

    @Given("several categories are created")
    public void severalCategoriesAreCreated() {
        var faker = new Faker();
        var createdCategories = IntStream.range(0, faker.number().numberBetween(3, 6))
            .mapToObj(i -> categoryRequester.create(CategoryMother.newCategory()).getBody())
            .toList();
        categoryWorld.setCreatedCategories(createdCategories);
    }
}
