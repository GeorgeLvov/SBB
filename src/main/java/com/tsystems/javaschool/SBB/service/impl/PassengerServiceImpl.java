package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.dto.PassengerInfoDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTOContainer;
import com.tsystems.javaschool.SBB.entities.Passenger;
import com.tsystems.javaschool.SBB.mapper.PassengerMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.PassengerRepository;
import com.tsystems.javaschool.SBB.service.interfaces.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
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
    public PassengerDTO getPassengerDTOById(int id) {
        Passenger passenger = passengerRepository.getPassengerById(id);
        return passengerMapper.toDTO(passenger);
    }

    @Override
    @Transactional
    public PassengerDTO findPassengerByPersonalData(String firstName, String lastName, Date birthDate){
        Passenger passenger = passengerRepository.getPassengerByPersonalData(firstName, lastName, birthDate);
        return passenger != null ? passengerMapper.toDTO(passenger) : null;
    }


    @Override
    @Transactional
    public boolean isPassengerAlreadyCheckedIn(String firstName, String lastName, Date birthDate, TicketDTOContainer ticketDTOContainer) {

        List<Object[]> list = passengerRepository.getPassengerWithTicketsByPersonalData(firstName, lastName, birthDate);

        for (Object[] objects : list) {
            int trainId = (int) objects[0];
            int tripId = (int) objects[1];
            Timestamp departureTime = (Timestamp) objects[2];
            Timestamp arrivalTime = (Timestamp) objects[3];
            if (trainId == ticketDTOContainer.getTrainDTO().getId() && tripId == ticketDTOContainer.getTripId()
                    && departureTime.equals(ticketDTOContainer.getDepartureTime()) && arrivalTime.equals(ticketDTOContainer.getArrivalTime())) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public List<PassengerInfoDTO> getAllPassengersByTrainIdAndTripId(int trainId, int tripId) {
        List<PassengerInfoDTO> passengers = new ArrayList<>();
        List<Object[]> objects = passengerRepository.getAllPassengersByTrainIdAndTripId(trainId, tripId);
        for (Object[] object : objects) {
            PassengerInfoDTO passengerInfoDTO = new PassengerInfoDTO((String) object[0], (String) object[1], (String) object[2], (Date) object[3],
                    (String) object[4], (String) object[5], (Timestamp) object[6], (Timestamp) object[7]);
            passengers.add(passengerInfoDTO);
        }

        return passengers;
    }

    @Override
    @Transactional
    public void addPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = passengerMapper.toEntity(passengerDTO);
        passengerRepository.add(passenger);
    }

}
