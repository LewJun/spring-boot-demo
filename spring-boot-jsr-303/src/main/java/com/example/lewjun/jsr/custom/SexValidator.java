package com.example.lewjun.jsr.custom;

import com.example.lewjun.enums.EnumSex;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SexValidator implements ConstraintValidator<Sex, Integer> {
    private boolean required;

    @Override
    public void initialize(final Sex constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(final Integer integer, final ConstraintValidatorContext constraintValidatorContext) {
        if (required) return valid(integer);
        if (integer == null) return true;
        return valid(integer);
    }

    private boolean valid(final Integer integer) {
        return Arrays.stream(EnumSex.values())
                .map(it -> it.code)
                .collect(Collectors.toList())
                .contains(integer);
    }
}
