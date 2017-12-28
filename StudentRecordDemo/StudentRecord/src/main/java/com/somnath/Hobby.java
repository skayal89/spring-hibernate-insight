package com.somnath;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=HobbyValidator.class)
public @interface Hobby {
	
	String[] hobbies(); // Mandatory
	//String[] hobbies() default {}; // Not Mandatory
	
	String message() default "Invalid hobby";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
