package com.tsystems.javaschool.SBB.validator;

import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.service.interfaces.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.Date;


@Component
public class TicketValidator implements Validator {

    @Autowired
    private PassengerService passengerService;

    @Override
    public boolean supports(Class<?> aClass) {
        return TicketDTO.class.equals(aClass);
    }
    @Override
    public void validate(Object o, Errors errors) {

        TicketDTO ticketDTO = (TicketDTO) o;

        if(ticketDTO.getBirthDate() == null){
            errors.rejectValue("birthDate", "Invalid.birthdate", "*Birthdate cannot be empty.");
            return;
        }

        if (passengerService.isPassengerAlreadyCheckedIn(ticketDTO.getPassengerName(), ticketDTO.getPassengerSurName(),
                Date.valueOf(ticketDTO.getBirthDate()), ticketDTO)) {
            errors.rejectValue("passengerName", "Duplicate.passenger");
        }

    }
}
