package com.tsystems.javaschool.SBB.dto;

import com.tsystems.javaschool.SBB.entities.Station;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripInfoDTO {
    private Station stationFrom;
    private Station stationTo;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
}
