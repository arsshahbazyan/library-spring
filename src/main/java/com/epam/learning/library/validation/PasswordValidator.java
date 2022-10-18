package com.epam.learning.library.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidator implements ConstraintValidator<PasswordValid, String> {

	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
	    return value != null && value.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}");
		
	}

}
