package com.epam.learning.library.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = FieldMatchValidator.class)
@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
public @interface FieldMatch {

	String message() default "Fields are not matching!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String first();

    String second();

    
    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
   
     @interface List{
        FieldMatch[] value();
    }
}
