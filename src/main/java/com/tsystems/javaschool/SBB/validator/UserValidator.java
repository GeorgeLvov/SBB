package com.tsystems.javaschool.SBB.validator;

import java.util.regex.Pattern;

import com.tsystems.javaschool.SBB.dto.UserDTO;
import com.tsystems.javaschool.SBB.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (userDTO.getUsername().length() < 4 || userDTO.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userService.findUserDTOByName(userDTO.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$",
                Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(userDTO.getUsername()).matches())) {
            errors.rejectValue("username", "Invalid.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (userDTO.getPassword().length() < 4) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }

    }
}