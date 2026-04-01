package com.afpr.pfm.finance.step.when;

import com.afpr.pfm.finance.step.worlds.CategoryWorld;
import com.afpr.pfm.finance.support.category.application.CategoryMother;
import com.afpr.pfm.finance.support.category.application.CategoryRequester;

import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WhenCategory {

    private final CategoryRequester requester;
    private final CategoryWorld world;

    @When("admin requests the category creation")
    public void adminRequestsCategoryCreation() {
        world.setLastResponse(requester.create(world.getCategory()));
    }

    @When("admin looks for the category")
    public void adminLooksForTheCategory() {
        world.setLastResponse(requester.getById(world.getCategoryId()));
    }

    @When("admin looks for the category list")
    public void adminLooksForTheCategoryList() {
        world.setLastResponse(requester.getAll(0, 100));
    }

    @When("admin requests a category edition")
    public void adminRequestsACategoryEdition() {
        var editedCategory = CategoryMother.withId(world.getCategory().getId());
        world.setCategory(editedCategory);
        world.setLastResponse(requester.update(editedCategory));
    }
}
