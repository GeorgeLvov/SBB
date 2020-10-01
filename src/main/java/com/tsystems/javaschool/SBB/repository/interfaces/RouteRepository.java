package com.tsystems.javaschool.SBB.repository.interfaces;

import com.tsystems.javaschool.SBB.entities.Trip;

import java.util.List;

public interface RouteRepository {

    public List<Trip> getAllRoutes();

    Trip getRouteById(int id);

    void add(Trip trip);
}
