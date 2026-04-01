package com.afpr.pfm.finance.support.category.application;

import com.afpr.pfm.finance.category.domain.Category;

import net.datafaker.Faker;

import java.util.UUID;

public class CategoryMother {
    private static final Faker FAKER = new Faker();

    public static Category newCategory() {
        return Category.builder()
                .name(generateName())
                .build();
    }

    public static Category withName(String name) {
        return Category.builder()
                .name(name)
                .build();
    }

    public static Category withId(UUID id) {
        return Category.builder()
                .id(id)
                .name(generateName())
                .build();
    }

    public static String generateName() {
        String rawName = FAKER.unique().fetchFromYaml("commerce.department");
        return rawName.replaceAll("[^a-zA-Z0-9 áéíóúÁÉÍÓÚüÜñÑ-]", "");
    }
}
