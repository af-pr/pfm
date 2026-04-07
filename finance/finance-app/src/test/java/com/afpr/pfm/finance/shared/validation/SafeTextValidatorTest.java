package com.afpr.pfm.finance.shared.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class SafeTextValidatorTest {

    private final SafeTextValidator underTest = new SafeTextValidator();

    @Test
    void isValid_null_returnsTrue() {
        assertThat(underTest.isValid(null, null)).isTrue();
    }

    @Test
    void isValid_blankString_returnsTrue() {
        assertThat(underTest.isValid("   ", null)).isTrue();
    }

    @Test
    void isValid_emptyString_returnsTrue() {
        assertThat(underTest.isValid("", null)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Basic Expenses", "Salud", "Ropa y calzado", "Category-1", "Test.value", "A,B", "Alimentación", "Ropa", "Médico", "Über"})
    void isValid_allowedCharacters_returnsTrue(String value) {
        assertThat(underTest.isValid(value, null)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"<script>", "Category@Home", "Name#1", "Value$100", "Test%Off", "A&B", "A/B", "A\\B"})
    void isValid_forbiddenCharacters_returnsFalse(String value) {
        assertThat(underTest.isValid(value, null)).isFalse();
    }
}
