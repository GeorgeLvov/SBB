package com.tsystems.javaschool.SBB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Data access object that provides info about one segment of all train's trip
 *
 * @author George Lvov
 * @version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripInfo {

    TrainDTO trainDTO;

    Integer tripId;

    private StationDTO stationFrom;

    private StationDTO stationTo;

    private Timestamp departureTime;

    private Timestamp arrivalTime;

}
