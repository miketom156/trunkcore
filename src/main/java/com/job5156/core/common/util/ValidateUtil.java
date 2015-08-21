package com.job5156.core.common.util;

import org.apache.commons.collections.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidateUtil {
    private static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static <T> boolean validate(T t) {
        Set<ConstraintViolation<T>> constraintViolations = validateResult(t);
        return CollectionUtils.isEmpty(constraintViolations);
    }

    public static <T> Set<ConstraintViolation<T>> validateResult(T t) {
        return validator.validate(t);
    }
}
