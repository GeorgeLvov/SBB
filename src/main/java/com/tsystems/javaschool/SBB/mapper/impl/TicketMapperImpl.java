package com.tsystems.javaschool.SBB.mapper.impl;

import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.entities.Ticket;
import com.tsystems.javaschool.SBB.mapper.interfaces.PassengerMapper;
import com.tsystems.javaschool.SBB.mapper.interfaces.TicketMapper;
import com.tsystems.javaschool.SBB.mapper.interfaces.UserMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.StationRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TrainRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketMapperImpl implements TicketMapper {

    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PassengerMapper passengerMapper;



    public TicketDTO toDTO(Ticket ticket) {
        if (ticket == null) {
            return null;
        }
        TicketDTO ticketDTO = TicketDTO.builder()
                .id(ticket.getId())
                .trainId(ticket.getTrain().getId())
                .tripId(ticket.getTrip().getId())
                .stationFromId(ticket.getStationFrom().getId())
                .stationToId(ticket.getStationTo().getId())
                .departureTime(ticket.getDepartureTime())
                .arrivalTime(ticket.getArrivalTime())
                .passengerDTO(passengerMapper.toDTO(ticket.getPassenger()))
                .userDTO(userMapper.toDTO(ticket.getUser()))
                .valid(ticket.isValid())
                .build();

        return ticketDTO;
    }

    public Ticket toEntity(TicketDTO ticketDTO) {
        if (ticketDTO == null) {
            return null;
        }

        Ticket ticket = Ticket.builder()
                .id(ticketDTO.getId())
                .train(trainRepository.getTrainById(ticketDTO.getTrainId()))
                .trip(tripRepository.getTripById(ticketDTO.getTripId()))
                .stationFrom(stationRepository.getStationById(ticketDTO.getStationFromId()))
                .stationTo(stationRepository.getStationById(ticketDTO.getStationToId()))
                .departureTime(ticketDTO.getDepartureTime())
                .arrivalTime(ticketDTO.getArrivalTime())
                .user(userMapper.toEntity(ticketDTO.getUserDTO()))
                .passenger(passengerMapper.toEntity(ticketDTO.getPassengerDTO()))
                .valid(ticketDTO.isValid())
                .build();

        return ticket;
    }

    public List<TicketDTO> toDTOList(List<Ticket> ticketList) {
        if (ticketList == null) {
            return null;
        }

        List<TicketDTO> list = new ArrayList<>(ticketList.size());
        for (Ticket ticket : ticketList) {
            list.add(toDTO(ticket));
        }

        return list;
    }

    public List<Ticket> toEntityList(List<TicketDTO> ticketDTOList) {

        if (ticketDTOList == null) {
            return null;
        }

        List<Ticket> list = new ArrayList<>(ticketDTOList.size());
        for (TicketDTO ticketDTO : ticketDTOList) {
            list.add(toEntity(ticketDTO));
        }
        return list;
    }
}


