package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.dto.TicketInfoDTO;
import com.tsystems.javaschool.SBB.entities.Ticket;
import com.tsystems.javaschool.SBB.entities.User;
import com.tsystems.javaschool.SBB.mapper.TicketMapper;
import com.tsystems.javaschool.SBB.mapper.UserMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.TicketRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TrainRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.UserRepository;
import com.tsystems.javaschool.SBB.service.interfaces.TicketService;
import com.tsystems.javaschool.SBB.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    @Transactional
    public boolean isTimeValid(String departureTimeStr) {
        long departureTime = Timestamp.valueOf(departureTimeStr).getTime();
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
    public boolean isTrainFull(String departureTimeStr, String arrivalTimeStr, int trainId, int tripId) {
        Timestamp departureTime = Timestamp.valueOf(departureTimeStr);
        Timestamp arrivalTime = Timestamp.valueOf(arrivalTimeStr);
        setValidityOfTickets();
        BigInteger bigInteger = ticketRepository.getTakenSeatsCount(trainId, tripId, departureTime, arrivalTime);
        return trainRepository.getTrainById(trainId).getCapacity() <= bigInteger.longValue();
    }

    @Override
    @Transactional
    public TicketDTO getTicketDTOById(int id) {
        Ticket ticket = ticketRepository.getTicketById(id);
        return ticketMapper.toDTO(ticket);
    }

    @Override
    @Transactional
    public List<TicketInfoDTO> getAllTicketInfosByUsername(String username) {

        User user = userRepository.findByUsername(username);

        List<Object[]> tickets = ticketRepository.getAllTicketsByUserId(user.getId());

        List<TicketInfoDTO> ticketInfos = new ArrayList<>();
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

            ticketInfos
                    .add(new TicketInfoDTO(ticketId, trainName, firstName, lastName, birthDate, statFromTitle, statToTitle, departureTime, arrivalTime, valid));
        }

        return ticketInfos;
    }


    @Override
    @Transactional
    public void add(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticketRepository.add(ticket);
    }


}
