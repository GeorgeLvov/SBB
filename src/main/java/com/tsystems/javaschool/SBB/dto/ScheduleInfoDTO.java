package com.tsystems.javaschool.SBB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleInfoDTO {
    int trainId;
    int stationFromId;
    List<StationDTO> stationDTOs;
    String departureTime;
   // List<LocalDateTimeClass> arrTimes;
}
