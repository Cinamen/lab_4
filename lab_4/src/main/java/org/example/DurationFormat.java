package org.example;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DurationFormatValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface DurationFormat {
    String message() default "Invalid time format or out of range (10-200 minutes)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
