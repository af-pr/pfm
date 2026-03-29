package com.afpr.pfm.finance.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Marks a field for validation that only allows safe text characters.
 * Details about allowed/disallowed characters are in SafeTextValidator.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SafeTextValidator.class)
@Documented
public @interface SafeText {
    String message() default "Text contains invalid characters. Only letters, numbers, spaces, hyphens, periods, and commas are allowed.";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
