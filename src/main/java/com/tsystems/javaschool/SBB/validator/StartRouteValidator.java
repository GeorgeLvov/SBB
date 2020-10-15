package com.tsystems.javaschool.SBB.validator;

import com.tsystems.javaschool.SBB.dto.RouteDTO;
import com.tsystems.javaschool.SBB.service.interfaces.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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
            errors.rejectValue("departureDate", "Blank.departureTime");
        }

        if (routeDTO.getDeclaredArrivalDate().equals("")) {
            errors.rejectValue("declaredArrivalDate", "Blank.arrivalTime");
        }

        if (!routeDTO.getDepartureDate().equals("") && !routeDTO.getDeclaredArrivalDate().equals("")
                && !routeDTO.getTrainName().equals("")) {

            try {

                if (LocalDateTime.parse(routeDTO.getDepartureDate()).compareTo(LocalDateTime.now().plusMinutes(5)) <= 0) {
                    errors.rejectValue("departureDate", "Invalid.time.current");
                }

                if (LocalDateTime.parse(routeDTO.getDepartureDate())
                        .compareTo(LocalDateTime.parse(routeDTO.getDeclaredArrivalDate())) >= 0) {
                    errors.rejectValue("declaredArrivalDate", "Invalid.arrivalTime");

                } else if (!isRouteDurationValid(routeDTO.getDepartureDate(),routeDTO.getDeclaredArrivalDate())) {
                    errors.rejectValue("declaredArrivalDate", "Invalid.route.duration");
                }

                if (!scheduleService.isTrainAvailableForNewTrip(routeDTO.getTrainName(), routeDTO.getDepartureDate(),
                        routeDTO.getDeclaredArrivalDate())) {
                    errors.rejectValue("trainName", "Train.is.unavailable");
                }
            }

            catch (DateTimeParseException dpe) {
                errors.rejectValue("departureDate", "Invalid.dateTime");
                errors.rejectValue("declaredArrivalDate", "Invalid.dateTime");
            }

        }
    }

    private boolean isRouteDurationValid(String departureTime, String arrivalTime){
        return (Timestamp.valueOf(LocalDateTime.parse(arrivalTime)).getTime()
                - Timestamp.valueOf(LocalDateTime.parse(departureTime)).getTime()) >= 900_000;
    }
}
