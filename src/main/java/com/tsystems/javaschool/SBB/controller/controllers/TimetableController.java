package com.tsystems.javaschool.SBB.controller.controllers;

import com.tsystems.javaschool.SBB.dto.*;


import com.tsystems.javaschool.SBB.repository.interfaces.ScheduleRepository;
import com.tsystems.javaschool.SBB.service.interfaces.ScheduleService;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class TimetableController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private StationService stationService;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping("/schedule/{stationId}")
    public List<TimetableDTO> getTimetable(@PathVariable("stationId") Integer stationId) {
        System.out.println("Пришёл реквест!!!!!!!!!!!!!!!!!!! ---->>>>>" + stationService.getStationDTOById(stationId).getTitle());
        List list = scheduleRepository.getTimetableByStationName(stationId);
        return list;
    }
}
