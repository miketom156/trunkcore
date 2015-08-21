package com.job5156.core.common.validator;

import com.job5156.core.common.Constants;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneFormatValidator.class)
@Documented
public @interface PhoneFormat {
    String message() default "phone.format.error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String value() default Constants.PATTERN_PHONE ;
}