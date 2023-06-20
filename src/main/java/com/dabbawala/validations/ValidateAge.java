package com.dabbawala.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidateAge1.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateAge {
    String message() default "age should be less than 18";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
