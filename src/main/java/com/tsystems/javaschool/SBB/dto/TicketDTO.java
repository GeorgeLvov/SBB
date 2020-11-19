package com.tsystems.javaschool.SBB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {

    private int id;

    private Integer trainId;

    private Integer tripId;

    private Integer stationFromId;

    private Integer stationToId;

    private Timestamp departureTime;

    private Timestamp arrivalTime;

    private PassengerDTO passengerDTO;

    private UserDTO userDTO;

    private boolean valid;
}

