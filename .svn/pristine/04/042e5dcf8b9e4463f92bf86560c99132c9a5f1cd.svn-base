package com.job5156.core.common.validator;

import org.joda.time.DateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.regex.Pattern;

public class DateTimeFormatValidator implements ConstraintValidator<DateFormat, Date> {

    private Pattern pattern;
    
    @Override
    public void initialize(DateFormat dateFormat) {
        this.pattern = Pattern.compile(dateFormat.value());
    }
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintContext) {
        if(date == null) {
            return true;
        }

        return this.pattern.matcher(new DateTime(date).toString("yyyy-MM-dd HH:mm:ss")).matches();

    }
    
}
