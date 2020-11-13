package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.dto.TicketInfo;
import com.tsystems.javaschool.SBB.entities.Passenger;
import com.tsystems.javaschool.SBB.entities.Ticket;
import com.tsystems.javaschool.SBB.entities.User;
import com.tsystems.javaschool.SBB.exception.NoTicketsException;
import com.tsystems.javaschool.SBB.mapper.TicketMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.*;
import com.tsystems.javaschool.SBB.service.interfaces.*;
import com.tsystems.javaschool.SBB.utils.TicketPDFExporter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private TicketPDFExporter pdfExporter;


    @Override
    @Transactional
    public void createTicket(TicketDTO ticketDTO) throws NoTicketsException {

        Ticket ticket = ticketMapper.toEntity(ticketDTO);

        ticket.setTrain(trainRepository.getTrainById(ticketDTO.getTrainId()));
        ticket.setTrip(tripRepository.getTripById(ticketDTO.getTripId()));
        ticket.setStationFrom(stationRepository.getStationById(ticketDTO.getStationFromId()));
        ticket.setStationTo(stationRepository.getStationById(ticketDTO.getStationToId()));
        ticket.setValid(true);

        String firstName = ticketDTO.getPassengerName();
        String lastName = ticketDTO.getPassengerSurName();
        Date birthDate = Date.valueOf(ticketDTO.getBirthDate());
        Passenger existingPassenger = passengerRepository.getPassengerByPersonalData(firstName, lastName, birthDate);
        if (existingPassenger != null) {
            ticket.setPassenger(existingPassenger);
        } else {
            Passenger passenger = new Passenger(firstName,lastName,birthDate);
            passengerRepository.add(passenger);
            ticket.setPassenger(passenger);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if (!username.equals("anonymousUser")) {
            ticket.setUser(userRepository.findUserByName(auth.getName()));
        }

        if(isTimeValid(ticket.getDepartureTime())
                && !isTrainFull(ticket.getDepartureTime(), ticket.getArrivalTime(),
                ticket.getTrain().getId(), ticket.getTrip().getId())){

            ticketRepository.add(ticket);

        } else {
            throw new NoTicketsException();
        }
    }


    @Override
    public void setValidityOfTickets() {
        ticketRepository.setValidityOfTickets();
    }

    @Override
    public boolean isTimeValid(Timestamp departureTimeIn) {
        long departureTime = departureTimeIn.getTime();
        long currentTime = Timestamp.valueOf(LocalDateTime.now()).getTime();
        return (departureTime - currentTime) >= 600000;
    }

    @Override
    public boolean isTrainFull(Timestamp departureTime, Timestamp arrivalTime, int trainId, int tripId) {
        setValidityOfTickets();
        BigInteger bigInteger = ticketRepository.getTakenSeatsCount(trainId, tripId, departureTime, arrivalTime);
        return trainRepository.getTrainById(trainId).getCapacity() <= bigInteger.longValue();
    }

    @Override
    @Transactional
    public List<TicketInfo> getAllUserTickets(String username) {
        User user = userRepository.findUserByName(username);
        setValidityOfTickets();
        List<Object[]> tickets = ticketRepository.getAllTicketsByUserId(user.getId());

        List<TicketInfo> userTickets = new ArrayList<>();

        for (Object[] objects : tickets) {
            int ticketId = (int) objects[0];
            String trainName = (String) objects[1];
            String firstName = (String) objects[2];
            String lastName = (String) objects[3];
            Date birthDate = (Date) objects[4];
            String statFromTitle = (String) objects[5];
            String statToTitle = (String) objects[6];
            Timestamp departureTime = (Timestamp) objects[7];
            Timestamp arrivalTime = (Timestamp) objects[8];
            boolean valid = (boolean) objects[9];

            userTickets.add(new TicketInfo(ticketId, trainName, firstName, lastName, birthDate, statFromTitle,
                            statToTitle, departureTime, arrivalTime, valid));
        }

        return userTickets;
    }

    @Override
    @Transactional
    public TicketDTO getTicketDTOById(int id) {
        setValidityOfTickets();
        Ticket ticket = ticketRepository.getTicketById(id);
        return ticketMapper.toDTO(ticket);
    }

    @Override
    public void exportToPDF(int id) {

        Ticket ticket = ticketRepository.getTicketById(id);

        pdfExporter.setTicket(ticket);
    }

}

