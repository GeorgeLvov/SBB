package com.tsystems.javaschool.SBB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

import java.sql.Timestamp;
import java.time.LocalDate;


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

    @NotBlank(message = "*Passenger name can't be empty.")
    @Size(min = 1, max = 100, message = "*Name must be between 1 and 100 characters.")
    @Pattern(regexp = "^[a-zA-Z \\-]+$", message = "*Please enter valid name.")
    private String passengerName;

    @NotBlank(message = "*Passenger surname can't be empty.")
    @Size(min = 1, max = 100, message = "*Surname must be between 1 and 100 characters.")
    @Pattern(regexp = "^[a-zA-Z \\-]+$", message = "*Please enter valid surname.")
    private String passengerSurName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "*Date of birth can't be later than now")
    private LocalDate birthDate;

    private UserDTO userDTO;

    private boolean valid;
}

