package com.tsystems.javaschool.SBB.service.interfaces;


import com.tsystems.javaschool.SBB.entities.Trip;

import java.util.List;


public interface RouteService {

    public List<Trip> getAllRoutes();

    Trip getRouteById(int id);

    void add(Trip trip);
}
