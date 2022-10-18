package com.epam.learning.library.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.learning.library.service.UserServiceImpl;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private UserServiceImpl userService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context){

		return value != null && !userService.isEmailAlreadyInUse(value);
	}
}
