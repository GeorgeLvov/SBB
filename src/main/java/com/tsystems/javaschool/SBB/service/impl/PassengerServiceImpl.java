package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.dto.PassengerInfo;
import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.entities.Passenger;
import com.tsystems.javaschool.SBB.mapper.interfaces.PassengerMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.PassengerRepository;
import com.tsystems.javaschool.SBB.service.interfaces.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private PassengerMapper passengerMapper;


    @Override
    @Transactional
    public boolean isPassengerAlreadyCheckedIn(TicketDTO ticketDTO) {

        List<Object[]> list = passengerRepository
                .getPassengerWithTicketsByPersonalData(ticketDTO.getPassengerDTO().getFirstName(),
                        ticketDTO.getPassengerDTO().getLastName(),
                        ticketDTO.getPassengerDTO().getBirthDate());

        for (Object[] objects : list) {
            int tripId = (int) objects[1];
            Timestamp departureTime = (Timestamp) objects[2];
            Timestamp arrivalTime = (Timestamp) objects[3];
            if (tripId == ticketDTO.getTripId() && departureTime.equals(ticketDTO.getDepartureTime())
                    && arrivalTime.equals(ticketDTO.getArrivalTime())) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public List<PassengerInfo> getAllPassengersByTrainIdAndTripId(int trainId, int tripId) {
        List<PassengerInfo> passengers = new ArrayList<>();
        List<Object[]> objects = passengerRepository.getAllPassengersByTrainIdAndTripId(trainId, tripId);
        for (Object[] object : objects) {
            PassengerInfo passengerInfo = new PassengerInfo((String) object[0], (String) object[1],
                    (String) object[2], (Date) object[3], (String) object[4], (String) object[5], (Timestamp) object[6],
                    (Timestamp) object[7]);
            passengers.add(passengerInfo);
        }

        return passengers;
    }


    @Override
    @Transactional
    public PassengerDTO findPassengerByPersonalData(String firstName, String lastName, LocalDate birthDate){
        Passenger passenger = passengerRepository.getPassengerByPersonalData(firstName, lastName, birthDate);
        return passenger != null ? passengerMapper.toDTO(passenger) : null;
    }

    @Override
    @Transactional
    public void addPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = passengerMapper.toEntity(passengerDTO);
        passengerRepository.add(passenger);
    }

}
