
package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.entities.*;
import com.tsystems.javaschool.SBB.exception.NoTicketsException;
import com.tsystems.javaschool.SBB.mapper.interfaces.TicketMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.PassengerRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TicketRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TrainRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.wildfly.common.Assert;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

    @InjectMocks
    private TicketServiceImpl ticketServiceImpl;

    @Mock
    private TrainRepository trainRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private TicketMapper ticketMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PassengerRepository passengerRepository;

    private static Ticket ticket;
    private static Train train;


    @Before
    public void setUp() {
        ticket = Ticket.builder()
                .train(new Train(1, "e200", 1))
                .trip(Trip.builder().id(1).build())
                .departureTime(Timestamp.valueOf("2022-10-10 10:00:00"))
                .arrivalTime(Timestamp.valueOf("2020-10-10 10:00:00"))
                .passenger(Passenger.builder().firstName("Bob").lastName("Martin")
                        .birthDate(LocalDate.parse("1952-12-05")).build())
                .build();

        train = Train.builder()
                .id(1)
                .trainName("e200")
                .capacity(1)
                .build();
    }

    @Test
    public void positiveCreateTicket() {
        Mockito.when(ticketMapper.toEntity(new TicketDTO())).thenReturn(ticket);

        Mockito.when(passengerRepository.getPassengerByPersonalData(anyString(), anyString(), any()))
                .thenReturn(new Passenger());

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("username");

        Mockito.when(userRepository.findUserByName(anyString())).thenReturn(new User());

        Mockito.when(ticketRepository.getTakenSeatsCount(anyInt(),anyInt(),any(),any()))
                .thenReturn(BigInteger.valueOf(0));

        Mockito.when(trainRepository.getTrainById(anyInt())).thenReturn(ticket.getTrain());

        Mockito.doNothing().when(ticketRepository).add(ticket);

        ticketServiceImpl.createTicket(new TicketDTO());
    }

    @Test(expected = NoTicketsException.class)
    public void negativeCreateTicketNoMoreSeats() {

        Mockito.when(ticketMapper.toEntity(new TicketDTO())).thenReturn(ticket);

        Mockito.when(passengerRepository.getPassengerByPersonalData(anyString(), anyString(), any()))
                .thenReturn(new Passenger());

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("username");

        Mockito.when(userRepository.findUserByName(anyString())).thenReturn(new User());

        Mockito.when(ticketRepository.getTakenSeatsCount(anyInt(),anyInt(),any(),any()))
                .thenReturn(BigInteger.valueOf(1));

        Mockito.when(trainRepository.getTrainById(anyInt())).thenReturn(ticket.getTrain());

        ticketServiceImpl.createTicket(new TicketDTO());
    }


    @Test
    public void trainIsFull() {
        Timestamp departure = new Timestamp(System.currentTimeMillis());
        Timestamp arrival = new Timestamp(System.currentTimeMillis());
        BigInteger bigInteger = BigInteger.valueOf(1);
        Mockito.when(ticketRepository.getTakenSeatsCount(train.getId(), 1, departure, arrival))
                .thenReturn(bigInteger);
        Mockito.when(trainRepository.getTrainById(anyInt())).thenReturn(train);
        Assert.assertTrue(ticketServiceImpl.isTrainFull(departure, arrival, train.getId(), 1));
    }

    @Test
    public void trainIsNotFull() {
        Timestamp departure = new Timestamp(System.currentTimeMillis());
        Timestamp arrival = new Timestamp(System.currentTimeMillis());
        BigInteger bigInteger = BigInteger.valueOf(0);
        Mockito.when(ticketRepository.getTakenSeatsCount(train.getId(), 1, departure, arrival))
                .thenReturn(bigInteger);
        Mockito.when(trainRepository.getTrainById(anyInt())).thenReturn(train);
        Assert.assertFalse(ticketServiceImpl.isTrainFull(departure, arrival, train.getId(), 1));

    }

    @Test
    public void timeIsValid() {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now().plusMinutes(10));
        Assert.assertTrue(ticketServiceImpl.isTimeValid(timestamp));
    }

    @Test
    public void timeIsNotValid() {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now().plusMinutes(9));
        Assert.assertFalse(ticketServiceImpl.isTimeValid(timestamp));
    }


}
