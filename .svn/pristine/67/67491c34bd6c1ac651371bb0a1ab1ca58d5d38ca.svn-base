package com.job5156.core.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneFormatValidator implements ConstraintValidator<PhoneFormat, String> {

	 private Pattern pattern;
	    
	    @Override
	    public void initialize(PhoneFormat regx) {
	        this.pattern = Pattern.compile(regx.value());
	    }
	    @Override
	    public boolean isValid(String inputString, ConstraintValidatorContext constraintContext) {
            if(inputString == null || inputString.length() == 0 ) {
                return true;
            }
            return this.pattern.matcher(inputString).matches();
        }
}
