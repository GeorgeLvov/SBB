package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTOContainer;
import com.tsystems.javaschool.SBB.dto.TicketInfoDTO;
import com.tsystems.javaschool.SBB.entities.Ticket;
import com.tsystems.javaschool.SBB.entities.User;
import com.tsystems.javaschool.SBB.exception.NoTicketsException;
import com.tsystems.javaschool.SBB.mapper.TicketMapper;
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
    private TicketDTOContainer ticketDTOContainer;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private StationService stationService;
    @Autowired
    private TrainService trainService;
    @Autowired
    private UserService userService;


    @Override
    @Transactional
    public TicketDTO getTicketDTOById(int id) {
        Ticket ticket = ticketRepository.getTicketById(id);
        return ticketMapper.toDTO(ticket);
    }

    @Override
    public boolean isTimeValid(Timestamp departureTimeIn) {
        long departureTime = departureTimeIn.getTime();
        long currentTime = Timestamp.valueOf(LocalDateTime.now()).getTime();
        return (departureTime - currentTime) >= 600000;
    }

    @Override
    @Transactional
    public void setValidityOfTickets() {
        ticketRepository.setValidityOfTickets();
    }

    @Override
    @Transactional
    public boolean isTrainFull(Timestamp departureTime, Timestamp arrivalTime, int trainId, int tripId) {
        setValidityOfTickets();
        BigInteger bigInteger = ticketRepository.getTakenSeatsCount(trainId, tripId, departureTime, arrivalTime);
        return trainRepository.getTrainById(trainId).getCapacity() <= bigInteger.longValue();
    }


    @Override
    @Transactional
    public void prepareTicketForPassenger(Map<String, String> allRequestParams) throws NoTicketsException{

        int trainId = Integer.parseInt(allRequestParams.get("trainId").trim());
        int tripId = Integer.parseInt(allRequestParams.get("tripId").trim());
        int stationFromId = Integer.parseInt(allRequestParams.get("stF").trim());
        int stationToId = Integer.parseInt(allRequestParams.get("stT").trim());
        Timestamp departureTime = Timestamp.valueOf(allRequestParams.get("departureTime"));
        Timestamp arrivalTime = Timestamp.valueOf(allRequestParams.get("arrivalTime"));

        if (isTimeValid(departureTime)
                && !isTrainFull(departureTime, arrivalTime, trainId, tripId)) {

            ticketDTOContainer.setTrainDTO(trainService.getTrainDTOById(trainId));
            ticketDTOContainer.setTripId(tripId);
            ticketDTOContainer.setStationFromDTO(stationService.getStationDTOById(stationFromId));
            ticketDTOContainer.setStationToDTO(stationService.getStationDTOById(stationToId));
            ticketDTOContainer.setDepartureTime(departureTime);
            ticketDTOContainer.setArrivalTime(arrivalTime);
            ticketDTOContainer.setValid(true);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            if (!username.equals("anonymousUser")) {
                ticketDTOContainer.setUserDTO(userService.findUserDTOByName(auth.getName()));
            }

        } else throw new NoTicketsException();
    }


    @Override
    public void setPassengerToTicket(PassengerDTO passengerDTO){
        String firstName = passengerDTO.getFirstName();
        String lastName = passengerDTO.getLastName();
        Date birthDate = passengerDTO.getBirthDate();
        PassengerDTO existingPassenger = passengerService.findPassengerByPersonalData(firstName, lastName, birthDate);
        if (existingPassenger != null) {
            ticketDTOContainer.setPassengerDTO(existingPassenger);
        } else {
            passengerService.addPassenger(passengerDTO);
            ticketDTOContainer.setPassengerDTO(passengerService.findPassengerByPersonalData(firstName, lastName, birthDate));
        }
    }


    @Override
    @Transactional
    public void createTicket(TicketDTOContainer ticketDTOContainer) throws NoTicketsException {

        if(isTimeValid(ticketDTOContainer.getDepartureTime())
                && !isTrainFull(ticketDTOContainer.getDepartureTime(), ticketDTOContainer.getArrivalTime(),
                ticketDTOContainer.getTrainDTO().getId(), ticketDTOContainer.getTripId())){

            TicketDTO ticketDTO = new TicketDTO(ticketDTOContainer);
            Ticket ticket = ticketMapper.toEntity(ticketDTO);
            ticketRepository.add(ticket);

        }

        else throw new NoTicketsException();

    }


    @Override
    @Transactional
    public List<TicketInfoDTO> getAllUserTickets(String username) {

        User user = userRepository.findUserByName(username);

        List<Object[]> tickets = ticketRepository.getAllTicketsByUserId(user.getId());

        List<TicketInfoDTO> userTickets = new ArrayList<>();
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
                    .add(new TicketInfoDTO(ticketId, trainName, firstName, lastName, birthDate, statFromTitle,
                            statToTitle, departureTime, arrivalTime, valid));
        }

        return userTickets;
    }

}
