package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.dto.UserDTO;
import com.tsystems.javaschool.SBB.entities.*;
import com.tsystems.javaschool.SBB.mapper.impl.TicketMapperImpl;
import com.tsystems.javaschool.SBB.repository.interfaces.PassengerRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TicketRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TrainRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyInt;


public class TicketServiceImplTest {

    @InjectMocks
    private TicketServiceImpl ticketServiceImpl;

    @Mock
    private TrainRepository trainRepository;

    @Mock
    private TicketRepository ticketRepository;


    private static Train train;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        train = Train.builder()
                .id(1)
                .trainName("e200")
                .capacity(1)
                .build();
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
