package com.tsystems.javaschool.SBB.validator;


import com.tsystems.javaschool.SBB.dto.RouteDTO;
import com.tsystems.javaschool.SBB.service.interfaces.ScheduleService;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class StartRouteValidator implements Validator {

    @Autowired
    ScheduleService scheduleService;

    @Override
    public boolean supports(Class<?> aClass) {
        return RouteDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        RouteDTO routeDTO = (RouteDTO) o;

        if (routeDTO.getDepartureDate().equals("")) {
            errors.rejectValue("departureDate", "BlankDept.time.BeginDTO");
        }
        if(LocalDateTime.parse(routeDTO.getDepartureDate()).compareTo(LocalDateTime.now().plusMinutes(30)) <= 0){
            errors.rejectValue("departureDate", "Invalid.time.current");
        }
        if (routeDTO.getDeclaredArrivalDate().equals("")) {
            errors.rejectValue("declaredArrivalDate", "BlankArr.time.BeginDTO");
        }


        if (!routeDTO.getDepartureDate().equals("") && !routeDTO.getDeclaredArrivalDate().equals("")) {
            if (LocalDateTime.parse(routeDTO.getDeclaredArrivalDate()).compareTo(LocalDateTime.parse(routeDTO.getDepartureDate())) < 0) {
                errors.rejectValue("declaredArrivalDate", "Invalid.time.BeginDTO.2");
            } else if ((Timestamp.valueOf(LocalDateTime.parse(routeDTO.getDeclaredArrivalDate())).getTime() - Timestamp.valueOf(LocalDateTime.parse(routeDTO.getDepartureDate())).getTime()) < 900_000) {

                errors.rejectValue("declaredArrivalDate", "Invalid.time.BeginDTO");
            }
        }

        if(!scheduleService.isTrainAvailableForNewTrip(routeDTO.getTrainName(), routeDTO.getDepartureDate(), routeDTO.getDeclaredArrivalDate())){
            errors.rejectValue("trainName", "Train.is.unavailable");
        }

    }
}
