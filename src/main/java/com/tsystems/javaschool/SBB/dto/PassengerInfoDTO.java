package com.tsystems.javaschool.SBB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerInfoDTO {

    public String trainName;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String stationFrom;

    private String stationTo;

    private Timestamp departureTime;

    private Timestamp arrivalTime;

}
