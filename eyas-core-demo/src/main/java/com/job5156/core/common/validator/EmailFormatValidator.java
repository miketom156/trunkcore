package com.job5156.core.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailFormatValidator implements ConstraintValidator<EmailFormat, String> {

	 private Pattern pattern;
	    
	    @Override
	    public void initialize(EmailFormat email) {
	        this.pattern = Pattern.compile(email.value(), Pattern.CASE_INSENSITIVE);
	    }
	    @Override
	    public boolean isValid(String email, ConstraintValidatorContext constraintContext) {
            if(email == null || email.length() == 0 ) {
                return true;
            }
            return this.pattern.matcher(email).matches();
        }
}
