package org.example;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Duration;

public class DurationFormatValidator implements ConstraintValidator<DurationFormat, Duration> {

    @Override
    public boolean isValid(Duration value, ConstraintValidatorContext context) {
        return value != null && value.toMinutes() >= 10 && value.toMinutes() <= 200;
    }
}
