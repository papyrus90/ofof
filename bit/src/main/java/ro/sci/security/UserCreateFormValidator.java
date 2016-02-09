package ro.sci.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ro.sci.domain.UserCreateForm;
import ro.sci.service.SqlService;

@Component
public class UserCreateFormValidator implements Validator{
	
	@Autowired
	SqlService service;

	@Override
	public boolean supports(Class<?> c) {
		return c.equals(UserCreateForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserCreateForm form = (UserCreateForm)target;
		validatePasswords(errors,form);
		validateEmail(errors,form);
	}
	
	private void validatePasswords(Errors errors,UserCreateForm form){
		if(!form.getPassword().equals(form.getPasswordRepeated())){
			errors.reject("password.no_match","passwords do not match");
			
		}
	}
	private void validateEmail(Errors errors,UserCreateForm form){
		if(service.getUserByEmail(form.getEmail())!= null ){
			errors.reject("email.exists", "User with this email already exists");
		}
	}
		
}
