package com.tsystems.javaschool.SBB.dto;

import com.tsystems.javaschool.SBB.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;


/**
 * Data access object that represents entity {@link Ticket}
 *
 * @author George Lvov
 * @version 1.0
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {

    private int id;

    private TrainDTO trainDTO;

    private Integer tripId;

    private StationDTO stationFromDTO;

    private StationDTO stationToDTO;

    private Timestamp departureTime;

    private Timestamp arrivalTime;

    private PassengerDTO passengerDTO;

    private UserDTO userDTO;

    private boolean valid;


    public TicketDTO(TicketDTOContainer container){
        trainDTO = container.getTrainDTO();
        tripId = container.getTripId();
        stationFromDTO = container.getStationFromDTO();
        stationToDTO = container.getStationToDTO();
        departureTime = container.getDepartureTime();
        arrivalTime = container.getArrivalTime();
        passengerDTO = container.getPassengerDTO();
        userDTO = container.getUserDTO();
        valid = container.isValid();
    }

}
