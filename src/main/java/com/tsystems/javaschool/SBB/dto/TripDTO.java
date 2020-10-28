package com.tsystems.javaschool.SBB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO {

    private int id;

    TrainDTO trainDTO;

    private StationDTO departureStationDTO;

    private StationDTO arrivalStationDTO;

    private Timestamp departureTime;

    private Timestamp arrivalTime;

    private int delay;

    private boolean canceled;

    private List<TripInfo> tripInfoList;

}
