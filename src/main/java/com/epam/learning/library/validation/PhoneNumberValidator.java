package com.epam.learning.library.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class PhoneNumberValidator 
			implements ConstraintValidator<PhoneNumber, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return value != null && value.matches("[0-9]{9}");
	}

	
}
