package com.tsystems.javaschool.SBB.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {

    private int id;

    private TrainDTO trainDTO;

    private TripDTO tripDTO;

    private int stationIndex;

    private StationDTO stationFromDTO;

    private StationDTO stationToDTO;

    private Timestamp departureTime;

    private Timestamp arrivalTime;

    private List<TripInfoDTO> tripInfoDTOList;

    public ScheduleDTO(int id, TrainDTO trainDTO, TripDTO tripDTO, int stationIndex, StationDTO stationFromDTO, StationDTO stationToDTO, Timestamp departureTime, Timestamp arrivalTime) {
        this.id = id;
        this.trainDTO = trainDTO;
        this.tripDTO = tripDTO;
        this.stationIndex = stationIndex;
        this.stationFromDTO = stationFromDTO;
        this.stationToDTO = stationToDTO;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}

