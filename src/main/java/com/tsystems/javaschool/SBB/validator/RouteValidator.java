package com.tsystems.javaschool.SBB.validator;

import com.tsystems.javaschool.SBB.dto.RouteDTO;
import com.tsystems.javaschool.SBB.dto.RouteDTOContainer;
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
    private RouteDTOContainer routeDTOContainer;

    @Override
    public boolean supports(Class<?> aClass) {
        return RouteDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        RouteDTO routeDTO = (RouteDTO) o;

        List<String> stationList = routeDTO.getSideStations();
        List<String> sideStations = routeDTOContainer.getSideStations();

        if (stationList != null && !stationList.isEmpty()) {
            if (sideStations == null || sideStations.isEmpty()) {
                if (stationList.get(0).equals(routeDTOContainer.getDepartureStationName())) {
                    errors.rejectValue("sideStations", "DuplicateStation.inRoute");
                }
            } else if (stationList.get(0).equals(sideStations.get(sideStations.size() - 1))) {
                errors.rejectValue("sideStations", "DuplicateStation.inRoute");
            }

        } else errors.rejectValue("sideStations", "Empty.station");


        List<String> resultArrTimes = routeDTOContainer.getSideArrivalTimes(); // в случае, если уже добавляли станции
        List<String> arrTimes = routeDTO.getSideArrivalTimes();

        if (arrTimes != null && !arrTimes.isEmpty()) { // если пришел не пустой лист, то работаем

            if (resultArrTimes == null || resultArrTimes.isEmpty()) { // если result лист пустой, т.е.первое добавление
                if (LocalDateTime.parse(arrTimes.get(0)).compareTo(LocalDateTime.parse(routeDTOContainer.getDeclaredArrivalDate())) > 0) { // если ввели время прибытия больше чем заявлено майн
                    errors.rejectValue("sideArrivalTimes", "TimeIsMoreThanMain");
                } else if (LocalDateTime.parse(arrTimes.get(0)).compareTo(LocalDateTime.parse(routeDTOContainer.getDepartureDate())) < 0) {
                    errors.rejectValue("sideArrivalTimes", "Invalid.routeTime");
                } else if (Timestamp.valueOf(LocalDateTime.parse(arrTimes.get(0))).getTime() - Timestamp.valueOf(LocalDateTime.parse(routeDTOContainer.getDepartureDate())).getTime() < 900_000) {
                    errors.rejectValue("sideArrivalTimes", "Invalid.time.BeginDTO"); // ввели раньше, чем после 15 мин после отбытия
                }

                // если в result уже что-то есть
            } else if (LocalDateTime.parse(arrTimes.get(0)).compareTo(LocalDateTime.parse(resultArrTimes.get(resultArrTimes.size() - 1)).plusMinutes(Long.parseLong(routeDTOContainer.getStops().get(routeDTOContainer.getStops().size() - 1)))) < 0) {
                errors.rejectValue("sideArrivalTimes", "Invalid.routeTime.2");

            } else if ((Timestamp.valueOf(LocalDateTime.parse(arrTimes.get(0))).getTime() - Timestamp.valueOf(LocalDateTime.parse(resultArrTimes.get(resultArrTimes.size() - 1)).plusMinutes(Long.parseLong(routeDTOContainer.getStops().get(routeDTOContainer.getStops().size() - 1)))).getTime()) < 900_000) {
                errors.rejectValue("sideArrivalTimes", "Invalid.time.BeginDTO");
            } else if (LocalDateTime.parse(arrTimes.get(0)).compareTo(LocalDateTime.parse(routeDTOContainer.getDeclaredArrivalDate())) > 0) {
                errors.rejectValue("sideArrivalTimes", "TimeIsMoreThanMain");
            }


        } else errors.rejectValue("sideArrivalTimes", "Empty.time"); // если пришел пустой лист


        List<String> stopsList = routeDTO.getStops();
        if (stopsList == null || stopsList.isEmpty()) {
            errors.rejectValue("stops", "Empty.stop.section");
        }

    }
}
