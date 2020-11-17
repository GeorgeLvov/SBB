package com.tsystems.javaschool.SBB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteContainer {

    private TrainDTO trainDTO;

    private StationDTO departureStation;

    private String departureDate;

    private String declaredArrivalDate;

    private List<StationDTO> sideStations;

    private List<String> sideArrivalTimes;

    private List<String> stops;
}
