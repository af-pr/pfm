package com.afpr.pfm.finance.support.category.application;

import com.afpr.pfm.finance.client.dto.CategoryCreateRequest;
import net.datafaker.Faker;

public class CategoryMother {
    private static final Faker FAKER = new Faker();

    public static CategoryCreateRequest newCategory() {
        return CategoryCreateRequest.builder()
                .name(generateName())
                .build();
    }

    public static CategoryCreateRequest withName(String name) {
        return CategoryCreateRequest.builder()
                .name(name)
                .build();
    }

    public static String generateName() {
        String rawName = FAKER.unique().fetchFromYaml("commerce.department");
        return rawName.replaceAll("[^a-zA-Z0-9 áéíóúÁÉÍÓÚüÜñÑ-]", "");
    }
}
