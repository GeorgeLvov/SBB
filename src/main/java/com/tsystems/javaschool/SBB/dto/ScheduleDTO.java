package com.tsystems.javaschool.SBB.dto;

import com.tsystems.javaschool.SBB.entities.Schedule;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Data access object that represents entity {@link Schedule}
 *
 *
 * @author George Lvov
 * @version 1.0
 */

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

    private int freePlacesCount;

    private boolean availableOnTime;

    private List<TripInfo> tripInfoList;

}

