package com.a2zshop.microservices.userinfoservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {WhitespaceValidator.class}
)
public @interface WhitespaceValidation {
    String message() default "{validation.message.whitespace}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}