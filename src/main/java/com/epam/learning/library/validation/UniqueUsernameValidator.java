package com.epam.learning.library.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.learning.library.service.UserServiceImpl;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{

	@Autowired
	private UserServiceImpl userService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && !userService.isUsernameAlreadyInUse(value);
	}

}
