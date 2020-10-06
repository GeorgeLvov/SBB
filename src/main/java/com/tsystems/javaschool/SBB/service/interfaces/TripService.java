package com.tsystems.javaschool.SBB.service.interfaces;


import com.tsystems.javaschool.SBB.dto.TripDTO;
import com.tsystems.javaschool.SBB.entities.Trip;

import java.util.List;


public interface TripService {

    List<TripDTO> getAllTripsDTO();

    TripDTO getTripById(int id);

    void add(TripDTO tripDTO);
}
