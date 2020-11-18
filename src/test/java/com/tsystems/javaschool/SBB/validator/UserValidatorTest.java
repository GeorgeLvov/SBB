package com.tsystems.javaschool.SBB.validator;

import com.tsystems.javaschool.SBB.dto.UserDTO;
import com.tsystems.javaschool.SBB.service.interfaces.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {

    @InjectMocks
    private UserValidator userValidator;
    @Mock
    private UserService userService;

    private Validator validator;

    private static UserDTO userDTO;
    private static Errors errors;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        userDTO = UserDTO.builder()
                .id(1)
                .username("user@gmail.com")
                .password("12345")
                .confirmPassword("12345")
                .build();
        errors = new BeanPropertyBindingResult(userDTO, "userDTO");
    }

    @Test
    public void invalidUserEmail() {
        userDTO.setUsername("user@gmail");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidConfirmPassword() {
        userDTO.setConfirmPassword("1234");
        when(userService.findUserDTOByName(anyString())).thenReturn(null);
        userValidator.validate(userDTO, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("confirmPassword"));
    }

    @Test
    public void validateUserDuplicate() {
        when(userService.findUserDTOByName(anyString())).thenReturn(new UserDTO());
        userValidator.validate(userDTO, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("username"));
    }


}