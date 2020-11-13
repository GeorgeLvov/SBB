package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.RouteDTO;


/*
* Service interface for {@link com.tsystems.javaschool.SBB.dto.RouteContainer}
*
* */
public interface RouteContainerService {


    void setInitialInfo(RouteDTO routeDTO);


    void setSegmentsInfo(RouteDTO routeDTO);


    void updateSegmentsInfo(RouteDTO routeDTO);


    void deleteLastChange();


    void truncateContainer();
}