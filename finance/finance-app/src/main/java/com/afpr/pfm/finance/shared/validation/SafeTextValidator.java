package com.afpr.pfm.finance.shared.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Constraint validator for the @SafeText annotation.
 * Allowed characters:
 * - Unicode letters
 * - Digits (0-9)
 * - Spaces
 * - Hyphens (-)
 * - Periods (.)
 * - Commas (,)
 */
public class SafeTextValidator implements ConstraintValidator<SafeText, String> {
    private static final Pattern SAFE_TEXT_PATTERN = Pattern.compile(
        "^[\\p{L}\\p{N}\\s\\-.,]*$"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (!SAFE_TEXT_PATTERN.matcher(value).matches()) {
            return false;
        }

        if (value.trim().isEmpty()) {
            return false;
        }

        return true;
    }
}
