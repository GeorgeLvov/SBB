package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.entities.Passenger;
import com.tsystems.javaschool.SBB.entities.Ticket;
import com.tsystems.javaschool.SBB.entities.Train;
import com.tsystems.javaschool.SBB.entities.Trip;
import com.tsystems.javaschool.SBB.mapper.interfaces.PassengerMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.PassengerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PassengerServiceImplTest {


    @InjectMocks
    private PassengerServiceImpl passengerService;
    @Mock
    private PassengerRepository passengerRepository;
    @Mock
    private PassengerMapper passengerMapper;

    private static TicketDTO ticketDTO;


    @Before
    public void setUp() {
        ticketDTO = TicketDTO.builder()
                .tripId(1)
                .passengerDTO(PassengerDTO.builder().firstName("Bob").lastName("Martin")
                        .birthDate(LocalDate.parse("1952-12-05")).build())
                .departureTime(Timestamp.valueOf("2020-01-01 12:00:00"))
                .arrivalTime(Timestamp.valueOf("2020-01-01 20:00:00"))
                .build();
    }


    @Test
    public void passengerIsAlreadyCheckedIn() {
        List<Object[]> list = new ArrayList<>();
        Object[] objects = {1, Timestamp.valueOf("2020-01-01 12:00:00"), Timestamp.valueOf("2020-01-01 20:00:00")};
        list.add(objects);
        when(passengerRepository.getPassengerWithTicketsByPersonalData(anyString(),anyString(),any()))
                .thenReturn(list);
        Assert.assertTrue(passengerService.isPassengerAlreadyCheckedIn(ticketDTO));
    }

    @Test
    public void passengerIsNotCheckedIn() {
        List<Object[]> list = new ArrayList<>();
        when(passengerRepository.getPassengerWithTicketsByPersonalData(anyString(),anyString(),any()))
                .thenReturn(list);
        Assert.assertFalse(passengerService.isPassengerAlreadyCheckedIn(ticketDTO));
    }

    @Test
    public void positiveFindPassengerByPersonalData() {
        PassengerDTO passengerDTO = new PassengerDTO();
        Passenger passenger = new Passenger();
        when(passengerRepository.getPassengerByPersonalData(anyString(),anyString(),any()))
                .thenReturn(passenger);
        when(passengerMapper.toDTO(passenger)).thenReturn(passengerDTO);
        Assert.assertNotNull(passengerService.findPassengerByPersonalData(anyString(),anyString(),any()));
    }

    @Test
    public void negativeFindPassengerByPersonalData() {
        when(passengerRepository.getPassengerByPersonalData(anyString(),anyString(),any()))
                .thenReturn(null);
        Assert.assertNull(passengerService.findPassengerByPersonalData(anyString(),anyString(),any()));
    }
}