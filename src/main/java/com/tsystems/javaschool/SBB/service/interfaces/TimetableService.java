package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.TimetableDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TimetableService {

    List<TimetableDTO> getDepartureTimetable(Integer stationId);

    List<TimetableDTO> getArrivalTimetable(Integer stationId);

}
