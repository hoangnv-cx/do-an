package com.hoang.travel.component;

import com.hoang.travel.entity.UserEntity;
import com.hoang.travel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class ValidateForm implements Validator {
    @Autowired
    private UserRepository userRepository;
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == UserEntity.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserEntity userEntity = (UserEntity) o;
        // Check the fields of AppUserForm.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "UserName is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "fullName is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passWord", "passWord is required.");
        if (userEntity.getId() == 0) {
            UserEntity findByEmail = userRepository.findByEmail(userEntity.getEmail());
            if (findByEmail != null) {
                // Email has been used by another account.
                errors.rejectValue("email", "Duplicate.userEntity.email");
            }
        }
        if (!errors.hasFieldErrors("userName")) {
            UserEntity dbUser = userRepository.findByUserName(userEntity.getUserName());
            if (dbUser != null) {
                // Username is not available.
                errors.rejectValue("userName", "Duplicate.appUserForm.userName");
            }
        }
    }
}
