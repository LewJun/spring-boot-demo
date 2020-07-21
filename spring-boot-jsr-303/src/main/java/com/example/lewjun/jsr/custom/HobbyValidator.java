package com.example.lewjun.jsr.custom;

import com.example.lewjun.enums.EnumHobby;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HobbyValidator implements ConstraintValidator<Hobby, List<Integer>> {

    private boolean required;

    @Override
    public void initialize(final Hobby constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(final List<Integer> integers, final ConstraintValidatorContext constraintValidatorContext) {
        if (required) return valid(integers);
        if (integers == null) return true;
        return valid(integers);
    }

    private boolean valid(final List<Integer> integers) {
        return Arrays.stream(EnumHobby.values())
                .map(it -> it.code)
                .collect(Collectors.toList())
                .containsAll(integers);
    }
}
