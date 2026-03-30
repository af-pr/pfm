package com.afpr.pfm.finance.category.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class Category {

    private final UUID id;
    private final String name;
}
