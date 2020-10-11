package com.tsystems.javaschool.SBB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDTO {

    @NotNull(message = "Train is not selected!")
    String trainName;

    @NotNull(message = "Station is not selected!")
    String departureStationName;

    String departureDate;

    String declaredArrivalDate;

    List<String> sideStations;

    List<String> sideArrivalTimes;

    List<String> stops;
}
