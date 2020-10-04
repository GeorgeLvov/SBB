package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.entities.Trip;
import com.tsystems.javaschool.SBB.repository.interfaces.TripRepository;
import com.tsystems.javaschool.SBB.service.interfaces.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    TripRepository routeRepository;

    @Override
    @Transactional
    public List<Trip> getAllRoutes() {
        return routeRepository.getAllRoutes();
    }

    @Override
    @Transactional
    public Trip getRouteById(int id) {
        return routeRepository.getRouteById(id);
    }

    @Override
    @Transactional
    public void add(Trip trip) {
        routeRepository.add(trip);
    }
}
