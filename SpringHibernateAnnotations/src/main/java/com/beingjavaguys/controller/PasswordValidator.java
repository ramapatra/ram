package com.beingjavaguys.controller;




import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.beingjavaguys.domain.Employee;
@Component
public class PasswordValidator implements org.springframework.validation.Validator{
	
	
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return Employee.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"required.password", "Field name is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reentered",
				"required.reentered", "Field name is required.");
		Employee emp=(Employee)target;
		
		if(!(emp.getPassword().equals(emp.getReentered()))){
			errors.rejectValue("password", "notmatch.password");
		}
	}

}
