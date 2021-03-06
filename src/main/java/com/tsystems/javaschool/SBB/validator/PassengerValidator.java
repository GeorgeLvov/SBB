package com.tsystems.javaschool.SBB.validator;

import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.service.interfaces.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class PassengerValidator implements Validator {

    @Autowired
    private PassengerService passengerService;

    @Override
    public boolean supports(Class<?> aClass) {
        return TicketDTO.class.equals(aClass);
    }
    @Override
    public void validate(Object o, Errors errors) {

        TicketDTO ticketDTO = (TicketDTO) o;

        if(ticketDTO.getPassengerDTO().getBirthDate() == null){
            errors.rejectValue("passengerDTO.birthDate", "Invalid.birthdate",
                    "*Birthdate cannot be empty.");
            return;
        }

        if (passengerService.isPassengerAlreadyCheckedIn(ticketDTO)) {
            errors.rejectValue("passengerDTO.firstName", "Duplicate.passenger");
        }


    }
}
