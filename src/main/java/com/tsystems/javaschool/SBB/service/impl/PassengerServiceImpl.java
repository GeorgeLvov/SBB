package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.entities.Passenger;
import com.tsystems.javaschool.SBB.repository.PassengerRepository;
import com.tsystems.javaschool.SBB.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Override
    @Transactional
    public List<Passenger> getAllPassengers() {
        return passengerRepository.getAllPassengers();
    }

    @Override
    @Transactional
    public Passenger getPassengerById(int id) {
        return passengerRepository.getPassengerById(id);
    }

    @Override
    @Transactional
    public void add(Passenger passenger) {
        passengerRepository.add(passenger);
    }

    @Override
    @Transactional
    public void update(Passenger passenger) {
        passengerRepository.update(passenger);
    }

    @Override
    @Transactional
    public void delete(Passenger passenger) {
        passengerRepository.delete(passenger);
    }
}
