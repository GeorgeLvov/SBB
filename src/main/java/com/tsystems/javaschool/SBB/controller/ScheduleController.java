package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.dto.ScheduleDTO;
import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.TripInfoDTO;
import com.tsystems.javaschool.SBB.entities.Schedule;
import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.mapper.StationMapper;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import com.tsystems.javaschool.SBB.service.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    ScheduleServiceImpl scheduleService;
    @Autowired
    StationService stationService;
    @Autowired
    StationMapper stationMapper;

    @GetMapping("/schedule")
    public ModelAndView method() {
        ModelAndView modelAndView = new ModelAndView();

        StationDTO stationDTO = stationService.getStationDTOById(2);
        StationDTO stationDTOto = stationService.getStationDTOById(3);
        Station station = stationMapper.toEntity(stationDTO);
        Station station2 = stationMapper.toEntity(stationDTOto);
        Timestamp tmp1 = Timestamp.valueOf("2020-09-01 10:40:00");
        Timestamp tmp2 = Timestamp.valueOf("2020-12-30 17:31:00");

        List<ScheduleDTO> list = scheduleService.getSchedulesByStationsAndDate(station, station2, tmp1, tmp2);
        list.forEach(System.out::println);

        for (ScheduleDTO scheduleDTO : list) {
            System.out.println(scheduleDTO.getTripInfoDTOList());
            System.out.println("-------------------------------------");
        }


        modelAndView.addObject("scheduleDTOList", list);
        modelAndView.setViewName("schedulePage");
        return modelAndView;
    }
}

/*
*   List<List<TripInfoDTO>> tripInfoOfAllTrains = new ArrayList<>();
        for (ScheduleDTO schedule : list) {
            List<TripInfoDTO> tripInfoDTOList = scheduleService.getInfoOfAllTripsByTrainId(schedule.getTrainDTO().getId(), schedule.getTripDTO().getId());
            tripInfoOfAllTrains.add(tripInfoDTOList);
        }

        for (List<TripInfoDTO> list3 : tripInfoOfAllTrains) {
            for (TripInfoDTO tripInfoDTO : list3) {
                System.out.print(tripInfoDTO + " ");
            }
            System.out.println();
        }*/