package com.afpr.pfm.finance.category.domain;

import java.util.UUID;

import net.datafaker.Faker;

public class CategoryMother {

    private static final Faker FAKER = new Faker();

    public static Category random() {
        return Category.builder()
                .id(UUID.randomUUID())
                .name(randomName())
                .build();
    }

    public static Category withoutId() {
        return Category.builder()
                .name(randomName())
                .build();
    }

    private static String randomName() {
        return FAKER.commerce().department();
    }
}
