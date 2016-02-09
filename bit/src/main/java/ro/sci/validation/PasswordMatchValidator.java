package ro.sci.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ro.sci.domain.UserCreateForm;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch,UserCreateForm> {

	@Override
	public void initialize(PasswordMatch passwordMatch) {
		
		
	}

	@Override
	public boolean isValid(UserCreateForm form, ConstraintValidatorContext ctx) {
		if (form.getPassword().equals(form.getPasswordRepeated())){
			return true;
		}
		return false;
	}




}
