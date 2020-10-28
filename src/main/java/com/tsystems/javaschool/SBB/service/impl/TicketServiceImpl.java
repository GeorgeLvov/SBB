package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.dto.TicketInfo;
import com.tsystems.javaschool.SBB.entities.Passenger;
import com.tsystems.javaschool.SBB.entities.Ticket;
import com.tsystems.javaschool.SBB.entities.User;
import com.tsystems.javaschool.SBB.exception.NoTicketsException;
import com.tsystems.javaschool.SBB.mapper.PassengerMapper;
import com.tsystems.javaschool.SBB.mapper.TicketMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.PassengerRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TicketRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TrainRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.UserRepository;
import com.tsystems.javaschool.SBB.service.interfaces.*;
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
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketDTO ticketDTO;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private PassengerMapper passengerMapper;
    @Autowired
    private StationService stationService;
    @Autowired
    private TrainService trainService;
    @Autowired
    private UserService userService;



    @Override
    @Transactional
    public TicketDTO getTicketDTOById(int id) {
        setValidityOfTickets();
        Ticket ticket = ticketRepository.getTicketById(id);
        return ticketMapper.toDTO(ticket);
    }

    @Override
    //@Transactional(propagation = Propagation.MANDATORY)
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
    public void prepareTicketForPassenger(Map<String, String> allRequestParams) throws NoTicketsException {

        int trainId = Integer.parseInt(allRequestParams.get("trainId").trim());
        int tripId = Integer.parseInt(allRequestParams.get("tripId").trim());
        int stationFromId = Integer.parseInt(allRequestParams.get("stF").trim());
        int stationToId = Integer.parseInt(allRequestParams.get("stT").trim());
        Timestamp departureTime = Timestamp.valueOf(allRequestParams.get("departureTime"));
        Timestamp arrivalTime = Timestamp.valueOf(allRequestParams.get("arrivalTime"));

        ticketDTO.setTrainDTO(trainService.getTrainDTOById(trainId));
        ticketDTO.setTripId(tripId);
        ticketDTO.setStationFromDTO(stationService.getStationDTOById(stationFromId));
        ticketDTO.setStationToDTO(stationService.getStationDTOById(stationToId));
        ticketDTO.setDepartureTime(departureTime);
        ticketDTO.setArrivalTime(arrivalTime);
        ticketDTO.setValid(true);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        if (!username.equals("anonymousUser")) {
            ticketDTO.setUserDTO(userService.findUserDTOByName(auth.getName()));
        }

    }


    @Override
    @Transactional
    public void setPassengerToTicket(PassengerDTO passengerDTO){
        String firstName = passengerDTO.getFirstName();
        String lastName = passengerDTO.getLastName();
        Date birthDate = passengerDTO.getBirthDate();
        Passenger existingPassenger = passengerRepository.getPassengerByPersonalData(firstName, lastName, birthDate);
        if (existingPassenger != null) {
            ticketDTO.setPassengerDTO(passengerMapper.toDTO(existingPassenger));
        } else {
            Passenger passenger = passengerMapper.toEntity(passengerDTO);
            passengerRepository.add(passenger);
            ticketDTO.setPassengerDTO(passengerMapper.toDTO(passenger));
        }
    }


    @Override
    @Transactional
    public void createTicket(TicketDTO ticketDTO) throws NoTicketsException {

        if(isTimeValid(ticketDTO.getDepartureTime())
                && !isTrainFull(ticketDTO.getDepartureTime(), ticketDTO.getArrivalTime(),
                ticketDTO.getTrainDTO().getId(), ticketDTO.getTripId())){

            Ticket ticket = ticketMapper.toEntity(ticketDTO);
            ticketRepository.add(ticket);
        }

        else throw new NoTicketsException();

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

            userTickets
                    .add(new TicketInfo(ticketId, trainName, firstName, lastName, birthDate, statFromTitle,
                            statToTitle, departureTime, arrivalTime, valid));
        }

        return userTickets;
    }

}

