package com.epam.learning.library.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = PostalCodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PostalCode {
	
	public String message() default "Incorrect postal code format!";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default{};

}
