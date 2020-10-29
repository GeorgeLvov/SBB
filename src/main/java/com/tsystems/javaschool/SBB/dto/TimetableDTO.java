package com.tsystems.javaschool.SBB.dto;

import java.sql.Timestamp;

public class TimetableDTO {

    private TrainDTO trainDTO;

    private StationDTO stationFromDTO;

    private StationDTO stationToDTO;

    private Timestamp departureTime;

    private Timestamp arrivalTime;

    private int delay;

    private boolean canceled;
}
