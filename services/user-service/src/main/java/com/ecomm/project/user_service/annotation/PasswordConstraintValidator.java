package com.ecomm.project.user_service.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    private int minLength;
    private int maxLength;
    private boolean mustContainUppercase;
    private boolean mustContainLowercase;
    private boolean mustContainDigit;
    private boolean mustContainSpecial;

    @Override
    public void initialize(ValidPassword constraint) {
        this.minLength = constraint.minLength();
        this.maxLength = constraint.maxLength();
        this.mustContainUppercase = constraint.mustContainUppercase();
        this.mustContainLowercase = constraint.mustContainLowercase();
        this.mustContainDigit = constraint.mustContainDigit();
        this.mustContainSpecial = constraint.mustContainSpecial();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.length() < minLength || password.length() > maxLength) {
            return false;
        }
        if (mustContainUppercase && !password.matches(".*[A-Z].*")) return false;
        if (mustContainLowercase && !password.matches(".*[a-z].*")) return false;
        if (mustContainDigit && !password.matches(".*\\d.*")) return false;
        if (mustContainSpecial && !password.matches(".*[!@#$%^&*()\\-+=].*")) return false;
        return true;
    }
}

