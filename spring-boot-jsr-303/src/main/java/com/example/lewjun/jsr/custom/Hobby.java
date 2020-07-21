package com.example.lewjun.jsr.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = HobbyValidator.class)
public @interface Hobby {
    boolean required() default false;

    String message() default "兴趣爱好错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
