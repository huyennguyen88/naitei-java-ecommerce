package com.triplet.validate;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.triplet.bean.UserInfo;
import com.triplet.model.User;

import org.apache.commons.lang3.StringUtils;

public class UserValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserInfo user = (UserInfo) target;

		if (StringUtils.isBlank(user.getUsername())) {
			errors.rejectValue("username", "NotEmpty");
		}

		if (StringUtils.isBlank(user.getEmail())) {
			errors.rejectValue("email", "NotEmpty");
		} else if (new EmailValidator().isValid(user.getEmail(), null) == false) {
			errors.rejectValue("email", "Error.Email.Format");
		}

		if (StringUtils.isBlank(user.getPassword())) {
			errors.rejectValue("password", "NotEmpty");
		} else if (user.getPassword().length() < 5) {
			errors.rejectValue("password", "Error.Pass.Size");
		} else if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Error.Pass.Incorrect");
		}

	}

}