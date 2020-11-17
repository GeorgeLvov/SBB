package com.tsystems.javaschool.SBB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDTO {

    @NotNull(message = "Train is not selected!")
    private Integer trainId;

    @NotNull(message = "Station is not selected!")
    private Integer departureStationId;

    @NotBlank(message = "*Departure time is not selected.")
    private String departureDate;

    @NotBlank(message = "*Arrival time is not selected.")
    private String declaredArrivalDate;

    private List<String> sideStations;

    private List<String> sideArrivalTimes;

    private List<String> stops;
}


