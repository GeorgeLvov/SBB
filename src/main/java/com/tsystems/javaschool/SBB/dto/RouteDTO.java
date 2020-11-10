package com.tsystems.javaschool.SBB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDTO {

    @NotBlank(message = "Train is not selected!")
    private String trainName;

    @NotBlank(message = "Station is not selected!")
    private String departureStationName;

    private String departureDate;

    private String declaredArrivalDate;

    private List<String> sideStations;

    private List<String> sideArrivalTimes;

    private List<String> stops;
}
