package com.tsystems.javaschool.SBB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketInfo {

    int ticketId;

    String trainName;

    String firstName;

    String lastName;

    Date birthDate;

    String statFromTitle;

    String statToTitle;

    Timestamp departureTime;

    Timestamp arrivalTime;

    boolean valid;

}
