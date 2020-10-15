package com.tsystems.javaschool.SBB.validator;

import com.tsystems.javaschool.SBB.dto.RouteDTO;
import com.tsystems.javaschool.SBB.dto.RouteDTOContainer;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class RouteValidator implements Validator {

    @Autowired
    private RouteDTOContainer container;

    @Override
    public boolean supports(Class<?> aClass) {
        return RouteDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        RouteDTO routeDTO = (RouteDTO) o;

        List<String> stationList = routeDTO.getSideStations();
        List<String> sideStations = container.getSideStations();

        if (CollectionUtils.isNotEmpty(stationList)) {
            if (CollectionUtils.isEmpty(sideStations)) {
                if (stationList.get(0).equals(container.getDepartureStationName())) {
                    errors.rejectValue("sideStations", "DuplicateStation.inRoute");
                }
            } else if (stationList.get(0).equals(sideStations.get(sideStations.size() - 1))) {
                errors.rejectValue("sideStations", "DuplicateStation.inRoute");
            }

        } else errors.rejectValue("sideStations", "Empty.station");



        List<String> arrTimes = routeDTO.getSideArrivalTimes();
        List<String> resultArrTimes = container.getSideArrivalTimes();
        List<String> resultStops = container.getStops();

        if (CollectionUtils.isNotEmpty(arrTimes)) {

            if (CollectionUtils.isEmpty(resultArrTimes)) {
                if (LocalDateTime.parse(arrTimes.get(0))
                        .compareTo(LocalDateTime.parse(container.getDeclaredArrivalDate())) > 0) {
                    errors.rejectValue("sideArrivalTimes", "TimeIsMoreThanMain");
                } else if (LocalDateTime.parse(arrTimes.get(0))
                        .compareTo(LocalDateTime.parse(container.getDepartureDate())) < 0) {
                    errors.rejectValue("sideArrivalTimes", "Invalid.routeTime");
                } else if ((Timestamp.valueOf(LocalDateTime.parse(arrTimes.get(0))).getTime()
                        - Timestamp.valueOf(LocalDateTime.parse(container.getDepartureDate())).getTime()) < 900_000) {
                    errors.rejectValue("sideArrivalTimes", "Invalid.route.duration");
                }

            }  else if(!isEnteredArrivalTimeValid(resultArrTimes.get(resultArrTimes.size() - 1), arrTimes.get(0),
                    resultStops.get(resultStops.size() - 1))) {
                errors.rejectValue("sideArrivalTimes", "Invalid.routeTime.2");
            } else if (LocalDateTime.parse(arrTimes.get(0)).compareTo(LocalDateTime.parse(container.getDeclaredArrivalDate())) > 0) {
                errors.rejectValue("sideArrivalTimes", "TimeIsMoreThanMain");
            }


        } else errors.rejectValue("sideArrivalTimes", "Empty.time");


        List<String> stopsList = routeDTO.getStops();
        if (CollectionUtils.isEmpty(stopsList)) {
            errors.rejectValue("stops", "Empty.stop.section");
        }
    }

    private boolean isEnteredArrivalTimeValid(String arrivalTime, String arrivalTimeToCompare, String stopDuration){
         return (Timestamp.valueOf(LocalDateTime.parse(arrivalTimeToCompare)).getTime() -
                Timestamp.valueOf(LocalDateTime.parse(arrivalTime)
                        .plusMinutes(Long.parseLong(stopDuration))).getTime()) >= 900_000;


    }
}
