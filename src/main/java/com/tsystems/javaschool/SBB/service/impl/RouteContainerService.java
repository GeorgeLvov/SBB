package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.RouteContainer;
import com.tsystems.javaschool.SBB.dto.RouteDTO;
import com.tsystems.javaschool.SBB.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteContainerService {

    @Autowired
    private RouteContainer routeContainer;


    public void updateListFields(RouteDTO routeDTO){
        routeContainer.getSideStations().addAll(routeDTO.getSideStations());
        routeContainer.getSideArrivalTimes().addAll(routeDTO.getSideArrivalTimes());
        routeContainer.getStops().addAll(routeDTO.getStops());
    }

    public void truncate(){
        routeContainer.setTrainName(null);
        routeContainer.setDepartureStationName(null);
        routeContainer.setDepartureDate(null);
        routeContainer.setSideStations(null);
        routeContainer.setSideArrivalTimes(null);
        routeContainer.setStops(null);
    }

    public void setFields(RouteDTO routeDTO, boolean flag){
        if(flag){
            routeContainer.setTrainName(routeDTO.getTrainName());
            routeContainer.setDepartureStationName(routeDTO.getDepartureStationName());
            routeContainer.setDepartureDate(routeDTO.getDepartureDate());
            routeContainer.setDeclaredArrivalDate(routeDTO.getDeclaredArrivalDate());
        } else{
            routeContainer.setSideArrivalTimes(routeDTO.getSideArrivalTimes());
            routeContainer.setSideStations(routeDTO.getSideStations());
            routeContainer.setStops(routeDTO.getStops());
        }
    }

    public void deleteLastChange(){
        CollectionUtils.removeLast(routeContainer.getSideStations());
        CollectionUtils.removeLast(routeContainer.getSideArrivalTimes());
        CollectionUtils.removeLast(routeContainer.getStops());
    }
}
