package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.TripDTO;
import com.tsystems.javaschool.SBB.entities.Trip;
import com.tsystems.javaschool.SBB.mapper.TripMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.TripRepository;
import com.tsystems.javaschool.SBB.service.interfaces.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    TripRepository tripRepository;
    @Autowired
    TripMapper tripMapper;

    @Override
    @Transactional
    public List<TripDTO> getAllTripsDTO() {
        List<Trip> trips = tripRepository.getAllTrips();
        return tripMapper.toDTOList(trips);
    }

    @Override
    @Transactional
    public TripDTO getTripById(int id) {
        Trip trip = tripRepository.getTripById(id);
        return tripMapper.toDTO(trip);
    }

    @Override
    @Transactional
    public void add(TripDTO tripDTO) {
        Trip trip = tripMapper.toEntity(tripDTO);
        tripRepository.addTrip(trip);
    }
}
