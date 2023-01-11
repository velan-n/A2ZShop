//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.a2zshop.microservices.productinfoservice.validation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {WhitespaceValidator.class}
)
public @interface WhitespaceValidation {
    String message() default "{validation.message.whitespace}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
