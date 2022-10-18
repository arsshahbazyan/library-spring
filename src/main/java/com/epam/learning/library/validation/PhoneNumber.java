package com.epam.learning.library.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = PhoneNumberValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface PhoneNumber {

	public String message() default "Incorrect phone number format!";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default{};
}
