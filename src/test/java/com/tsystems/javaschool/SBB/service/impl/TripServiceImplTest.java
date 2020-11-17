package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.entities.Train;
import com.tsystems.javaschool.SBB.entities.Trip;
import com.tsystems.javaschool.SBB.exception.TripCompletedException;
import com.tsystems.javaschool.SBB.repository.interfaces.TripRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyInt;


public class TripServiceImplTest {

    @InjectMocks
    private TripServiceImpl tripServiceImpl;

    @Mock
    private TripRepository tripRepository;

    @Mock
    private MessageSender messageSender;

    private static Trip trip;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        trip = Trip.builder()
                .id(1)
                .train(new Train())
                .departureStation(new Station())
                .arrivalStation(new Station())
                .departureTime(Timestamp.valueOf(LocalDateTime.now().plusMinutes(10)))
                .arrivalTime(Timestamp.valueOf("2020-10-10 10:10:00"))
                .canceled(false)
                .build();
    }

    @Test
    public void positiveCancelTrip() {
        trip.setDepartureTime(Timestamp.valueOf(LocalDateTime.now().plusMinutes(1)));
        Mockito.when(tripRepository.getTripById(anyInt())).thenReturn(trip);
        tripServiceImpl.cancelTrip(trip.getId());
    }

    @Test(expected = TripCompletedException.class)
    public void negativeCancelTrip() {
        trip.setDepartureTime(Timestamp.valueOf(LocalDateTime.now().minusMinutes(1)));
        Mockito.when(tripRepository.getTripById(anyInt())).thenReturn(trip);
        tripServiceImpl.cancelTrip(trip.getId());
    }



}