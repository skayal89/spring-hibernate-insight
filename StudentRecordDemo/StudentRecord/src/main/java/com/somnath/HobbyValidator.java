package com.somnath;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HobbyValidator implements ConstraintValidator<Hobby, String> {

	String validHobbies[];
	@Override
	public void initialize(Hobby constraintAnnotation) {
		// TODO Auto-generated method stub
		validHobbies = constraintAnnotation.hobbies();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		/*if(value.matches("Music|Movie|Sport"))
				return true;*/
		for(String hobby : validHobbies)
		{
			if(hobby.equalsIgnoreCase(value))
				return true;
		}
		return false;
	}

	
}
