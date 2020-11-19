package com.tsystems.javaschool.SBB.validator;

import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.service.interfaces.PassengerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PassengerValidatorTest {

    @InjectMocks
    private PassengerValidator passengerValidator;

    @Mock
    private PassengerService passengerService;

    private Validator validator;

    private static TicketDTO ticketDTO;
    private static PassengerDTO passengerDTO;
    private static Errors errors;


    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        passengerDTO = PassengerDTO.builder()
                .firstName("Bob")
                .lastName("Martin")
                .birthDate(LocalDate.parse("1952-12-05"))
                .build();

        ticketDTO = TicketDTO.builder()
                .passengerDTO(passengerDTO)
                .build();

        errors = new BeanPropertyBindingResult(ticketDTO, "ticketDTO");
    }


    @Test
    public void invalidPassengerBirthDate() {
        passengerDTO.setBirthDate(LocalDate.now().plusDays(1));
        Set<ConstraintViolation<PassengerDTO>> violations = validator.validate(passengerDTO);
        assertFalse(violations.isEmpty());
    }


    @Test
    public void validateValidPassenger() {
        when(passengerService.isPassengerAlreadyCheckedIn(ticketDTO)).thenReturn(false);
        passengerValidator.validate(ticketDTO, errors);
        assertFalse(errors.hasErrors());
        assertNull(errors.getFieldError("passengerDTO.birthDate"));
    }


    @Test
    public void validatePassengerBirthDate() {
        passengerDTO.setBirthDate(null);
        passengerValidator.validate(ticketDTO, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("passengerDTO.birthDate"));
    }

    @Test
    public void validatePassengerDuplicate() {
        when(passengerService.isPassengerAlreadyCheckedIn(ticketDTO)).thenReturn(true);
        passengerValidator.validate(ticketDTO, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("passengerDTO.firstName"));
    }


}