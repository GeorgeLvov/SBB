package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dao.PassengerDAO;
import com.tsystems.javaschool.SBB.entities.Passenger;
import com.tsystems.javaschool.SBB.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerDAO passengerDAO;

    @Override
    @Transactional
    public List<Passenger> getAllPassengers() {
        return passengerDAO.getAllPassengers();
    }

    @Override
    @Transactional
    public Passenger getPassengerById(int id) {
        return passengerDAO.getPassengerById(id);
    }

    @Override
    @Transactional
    public void add(Passenger passenger) {
        passengerDAO.add(passenger);
    }

    @Override
    @Transactional
    public void update(Passenger passenger) {
        passengerDAO.update(passenger);
    }

    @Override
    @Transactional
    public void delete(Passenger passenger) {
        passengerDAO.delete(passenger);
    }
}
