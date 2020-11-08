package com.tsystems.javaschool.SBB.validator;


import com.tsystems.javaschool.SBB.dto.UserDTO;
import com.tsystems.javaschool.SBB.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
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

        if (userService.findUserDTOByName(userDTO.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }

    }
}