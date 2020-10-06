package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.entities.Ticket;
import com.tsystems.javaschool.SBB.mapper.TicketMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.TicketRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TrainRepository;
import com.tsystems.javaschool.SBB.service.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TrainRepository trainRepository;
    @Autowired
    TicketMapper ticketMapper;


    @Override
    @Transactional
    public boolean isTimeValid(String departureTimeStr) {
        long departureTime = Timestamp.valueOf(departureTimeStr).getTime();
        long currentTime = Timestamp.valueOf(LocalDateTime.now()).getTime();
        return (departureTime - currentTime) >= 600000;
    }


    @Override
    @Transactional
    public boolean isTrainFull(String departureTimeStr, String arrivalTimeStr, int trainId, int tripId) {
        Timestamp departureTime = Timestamp.valueOf(departureTimeStr);
        Timestamp arrivalTime = Timestamp.valueOf(arrivalTimeStr);
        setValidityOfTickets();
        BigInteger bigInteger = ticketRepository.getTakenSeatsCount(trainId, tripId, departureTime, arrivalTime);
        return trainRepository.getTrainById(trainId).getCapacity() <= bigInteger.longValue();
    }

    @Override
    @Transactional
    public void setValidityOfTickets() {
        ticketRepository.setValidityOfTickets();
    }

    @Override
    @Transactional
    public void add(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticketRepository.add(ticket);
    }


}
