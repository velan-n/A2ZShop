package com.a2zshop.microservices.productinfoservice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WhitespaceValidator implements ConstraintValidator<WhitespaceValidation, String> {

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {

            if (str==null || str.startsWith(" ") || str.endsWith(" "))
                return false;

        return true;
    }
}
