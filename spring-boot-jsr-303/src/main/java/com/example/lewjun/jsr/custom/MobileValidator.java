package com.example.lewjun.jsr.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileValidator implements ConstraintValidator<Mobile, String> {
    public static final String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";

    private boolean required = false;

    @Override
    public void initialize(final Mobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(final String s, final ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return isMobile(s);
        } else {
            if (s == null) {
                return true;
            } else {
                return isMobile(s);
            }
        }
    }


    private boolean isMobile(final String phone) {
        if (phone.length() != 11) {
            return false;
        } else {
            final Pattern p = Pattern.compile(regex);
            final Matcher m = p.matcher(phone);
            return m.matches();
        }
    }
}
