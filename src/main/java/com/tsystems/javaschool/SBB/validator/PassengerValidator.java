package com.tsystems.javaschool.SBB.validator;


import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.service.interfaces.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Component
public class PassengerValidator implements Validator {

    @Autowired
    PassengerService passengerService;
    @Autowired
    TicketDTO ticketDTO;

    @Override
    public boolean supports(Class<?> aClass) {
        return PassengerDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        PassengerDTO passengerDTO = (PassengerDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Required");
        if (passengerDTO.getFirstName().length() < 1 || passengerDTO.getFirstName().length() > 100) {
            errors.rejectValue("firstName", "Size.firstName");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Required");
        if (passengerDTO.getFirstName().length() < 1 || passengerDTO.getFirstName().length() > 100) {
            errors.rejectValue("lastName", "Size.lastName");
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z \\-]+$",
                Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(passengerDTO.getFirstName()).matches())) {
            errors.rejectValue("firstName", "Invalid.name");
        }
        if (!(pattern.matcher(passengerDTO.getLastName()).matches())) {
            errors.rejectValue("lastName", "Invalid.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "Required");
        if (passengerDTO.getBirthDate() != null) {
            if (passengerDTO.getBirthDate().toLocalDate().compareTo(LocalDate.now()) > 0) {
                errors.rejectValue("birthDate", "Invalid.birthdate");
            }
        }

        if (passengerService.isPassengerAlreadyCheckedIn(passengerDTO.getFirstName(), passengerDTO.getLastName(),
                passengerDTO.getBirthDate(), ticketDTO)) {
            errors.rejectValue("firstName", "Duplicate.passenger");
            errors.rejectValue("lastName", "Duplicate.passenger");
            errors.rejectValue("birthDate", "Duplicate.passenger");
        }

    }
}

