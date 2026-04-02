package com.afpr.pfm.finance.support.category.domain;

import java.util.UUID;

import com.afpr.pfm.finance.category.domain.Category;

import net.datafaker.Faker;

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
        var rawName = FAKER.commerce().department();
        var sanitizedName = rawName.replaceAll("[^a-zA-Z0-9 áéíóúÁÉÍÓÚüÜñÑ-]", "");
        String randomSuffix = FAKER.regexify("[a-zA-Z0-9]{5}");
        return sanitizedName.concat(randomSuffix);
    }
}
