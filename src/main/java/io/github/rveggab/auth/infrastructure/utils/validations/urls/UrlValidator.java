package io.github.rveggab.auth.infrastructure.utils.validations.urls;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UrlValidator implements ConstraintValidator<ValidUrl, String> {

    private static final String URL_REGEX =
            "^https?:\\/\\/((([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,})|(\\d{1,3}(\\.\\d{1,3}){3}))(:\\d+)?(\\/[^\\s]*)?$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(URL_REGEX);
    }
}
