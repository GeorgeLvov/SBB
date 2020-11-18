
package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.entities.*;
import com.tsystems.javaschool.SBB.mapper.interfaces.PassengerMapper;
import com.tsystems.javaschool.SBB.mapper.interfaces.TicketMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.PassengerRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TicketRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TrainRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.wildfly.common.Assert;

import java.math.BigInteger;
import java.sql.Timestamp;
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


    @Test
    public void trainIsFull() {
        Train train = Train.builder()
                .id(1)
                .trainName("e200")
                .capacity(1)
                .build();
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
        Train train = Train.builder()
                .id(1)
                .trainName("e200")
                .capacity(1)
                .build();
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
