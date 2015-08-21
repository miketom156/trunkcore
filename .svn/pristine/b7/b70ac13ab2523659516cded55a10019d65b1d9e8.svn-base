package com.job5156.core.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class MobileFormatValidator implements ConstraintValidator<MobileFormat, String> {

	 private Pattern pattern;
	    
	    @Override
	    public void initialize(MobileFormat mobile) {
	        this.pattern = Pattern.compile(mobile.value());
	    }
	    @Override
	    public boolean isValid(String mobile, ConstraintValidatorContext constraintContext) {
            if(mobile == null || mobile.length() == 0 ) {
                return true;
            }
            return this.pattern.matcher(mobile).matches();
        }
}
