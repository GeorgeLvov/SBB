package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.TimetableDTO;
import com.tsystems.javaschool.SBB.repository.interfaces.ScheduleRepository;
import com.tsystems.javaschool.SBB.service.interfaces.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TimetableServiceImpl implements TimetableService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    @Transactional
    public List<TimetableDTO> getDepartureTimetable(Integer stationId) {
        return scheduleRepository.getDepartureTimetableByStationId(stationId);
    }

    @Override
    @Transactional
    public List<TimetableDTO> getArrivalTimetable(Integer stationId) {
        return scheduleRepository.getArrivalTimetableByStationId(stationId);
    }

}
