package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.entities.Trip;
import com.tsystems.javaschool.SBB.exception.TripCompletedException;
import com.tsystems.javaschool.SBB.repository.interfaces.ScheduleRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TripRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceImplTest {

    @InjectMocks
    private TripServiceImpl tripServiceImpl;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private TripRepository tripRepository;

    @Mock
    private MessageSender messageSender;



    @Test
    public void trainIsAvailableForSuchTrip() {
        List<Trip> trips = new ArrayList<>();

        Mockito.when(tripRepository.getTripsByTrainId(anyInt())).thenReturn(trips);

        boolean result = tripServiceImpl.isTrainAvailableForSuchTrip(1, "2020-10-01T17:00:00",
                "2020-10-03T17:00:00");

        Assert.assertTrue(result);
    }

    @Test
    public void trainIsNotAvailableForSuchTrip() {
        List<Trip> trips = new ArrayList<>();

        Trip trip1 = Trip.builder()
                .departureTime(Timestamp.valueOf("2020-10-01 17:00:00"))
                .arrivalTime(Timestamp.valueOf("2020-10-03 17:00:00"))
                .build();
        Trip trip2 = Trip.builder()
                .departureTime(Timestamp.valueOf("2020-10-01 16:00:00"))
                .arrivalTime(Timestamp.valueOf("2020-10-02 17:00:00"))
                .build();
        Trip trip3 = Trip.builder()
                .departureTime(Timestamp.valueOf("2020-10-01 18:00:00"))
                .arrivalTime(Timestamp.valueOf("2020-10-03 16:00:00"))
                .build();

        Collections.addAll(trips, trip1, trip2, trip3);

        Mockito.when(tripRepository.getTripsByTrainId(anyInt())).thenReturn(trips);

        boolean result = tripServiceImpl.isTrainAvailableForSuchTrip(1, "2020-10-01T17:00:00",
                "2020-10-03T17:00:00");

        Assert.assertFalse(result);
    }

    @Test
    public void positiveDelayTrip() {
        Trip trip = Trip.builder()
                .id(1)
                .arrivalTime(Timestamp.valueOf(LocalDateTime.now().plusMinutes(1)))
                .build();

        Mockito.when(tripRepository.getTripById(trip.getId())).thenReturn(trip);
        Mockito.doNothing().when(tripRepository).updateDepartureAndArrivalTimes(anyInt(), anyString());
        Mockito.doNothing().when(scheduleRepository).updateTimes(anyInt(), anyInt(), anyString());

        tripServiceImpl.delayTrip(trip.getId(), 10);

    }

    @Test(expected = TripCompletedException.class)
    public void negativeDelayTrip() {
        Trip trip = Trip.builder()
                .id(1)
                .arrivalTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        Mockito.when(tripRepository.getTripById(trip.getId())).thenReturn(trip);

        tripServiceImpl.delayTrip(trip.getId(), 10);
    }

    @Test
    public void positiveCancelTrip() {
        Trip trip = Trip.builder()
                .id(1)
                .departureTime(Timestamp.valueOf(LocalDateTime.now().plusMinutes(1)))
                .build();

        Mockito.when(tripRepository.getTripById(trip.getId())).thenReturn(trip);

        Mockito.doNothing().when(tripRepository).cancelTrip(trip.getId());

        tripServiceImpl.cancelTrip(trip.getId());
    }


    @Test(expected = TripCompletedException.class)
    public void negativeCancelTrip() {
        Trip trip = Trip.builder()
                .id(1)
                .departureTime(Timestamp.valueOf(LocalDateTime.now().minusMinutes(1)))
                .build();

        Mockito.when(tripRepository.getTripById(trip.getId())).thenReturn(trip);

        tripServiceImpl.cancelTrip(trip.getId());
    }



}