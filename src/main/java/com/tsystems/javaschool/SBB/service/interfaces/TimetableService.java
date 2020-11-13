package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.TimetableDTO;


import java.util.List;


public interface TimetableService {

    /**
     * Provides departure timetable for timetable application
     *
     * @return list of data transfer objects {@link TimetableDTO}
     * */
    List<TimetableDTO> getDepartureTimetable(Integer stationId);


    /**
     * Provides arrival timetable for timetable application
     *
     * @return list of data transfer objects {@link TimetableDTO}
     * */
    List<TimetableDTO> getArrivalTimetable(Integer stationId);

}
