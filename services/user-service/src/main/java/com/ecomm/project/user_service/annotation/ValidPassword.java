package com.ecomm.project.user_service.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "{password.invalid}";

    int minLength() default 8;

    int maxLength() default 20;

    boolean mustContainUppercase() default true;

    boolean mustContainLowercase() default true;

    boolean mustContainDigit() default true;

    boolean mustContainSpecial() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
