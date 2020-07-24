package com.example.lewjun;

import com.example.lewjun.domain.Ab01;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

public class ValidatorTest {
    @Test
    public void testValid() {
        final Ab01 ab01 = new Ab01();
        ab01.setAab002("部门名称");
        ab01.setAab003("部门所在地");

        final Set<ConstraintViolation<Ab01>> validations = Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(ab01);

        System.out.println(validations);
    }
}
