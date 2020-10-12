package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.RouteDTOContainer;
import com.tsystems.javaschool.SBB.dto.RouteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteDTOContainerService {

    @Autowired
    RouteDTOContainer routeDTOContainer;


    public void updateListFields(RouteDTO routeDTO){
        routeDTOContainer.getSideStations().addAll(routeDTO.getSideStations());
        routeDTOContainer.getSideArrivalTimes().addAll(routeDTO.getSideArrivalTimes());
        routeDTOContainer.getStops().addAll(routeDTO.getStops());
    }

    public void truncate(){
        routeDTOContainer.setTrainName(null);
        routeDTOContainer.setDepartureStationName(null);
        routeDTOContainer.setDepartureDate(null);
        routeDTOContainer.setSideStations(null);
        routeDTOContainer.setSideArrivalTimes(null);
        routeDTOContainer.setStops(null);
    }

    public void setFields(RouteDTO routeDTO, boolean flag){
        if(flag){
            routeDTOContainer.setTrainName(routeDTO.getTrainName());
            routeDTOContainer.setDepartureStationName(routeDTO.getDepartureStationName());
            routeDTOContainer.setDepartureDate(routeDTO.getDepartureDate());
            routeDTOContainer.setDeclaredArrivalDate(routeDTO.getDeclaredArrivalDate());
        } else{
            routeDTOContainer.setSideArrivalTimes(routeDTO.getSideArrivalTimes());
            routeDTOContainer.setSideStations(routeDTO.getSideStations());
            routeDTOContainer.setStops(routeDTO.getStops());
        }
    }

    public void deleteLastChange(){
        routeDTOContainer.getSideStations().remove(routeDTOContainer.getSideStations().size() - 1);
        routeDTOContainer.getSideArrivalTimes().remove(routeDTOContainer.getSideArrivalTimes().size() - 1);
        routeDTOContainer.getStops().remove(routeDTOContainer.getStops().size() - 1);
    }
}
