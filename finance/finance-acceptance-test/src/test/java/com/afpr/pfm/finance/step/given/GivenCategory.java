package com.afpr.pfm.finance.step.given;

import com.afpr.pfm.finance.step.worlds.CategoryWorld;
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
        categoryWorld.setCategoryName(new Faker().color().name());
    }

    @Given("a category is created")
    public void aCategoryIsCreated() {
        aValidCategoryIsDeclared();
        categoryWorld.setLastResponse(categoryRequester.create(categoryWorld.getCategoryName()));
    }

    @And("another category with the same name")
    public void anotherCategoryWithTheSameName() {
        // No implementation needed: the category name is already stored in categoryWorld
    }
}
