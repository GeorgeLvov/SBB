package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.dto.ScheduleDTO;
import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.mapper.StationMapper;
import com.tsystems.javaschool.SBB.service.interfaces.ScheduleService;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    StationService stationService;
    @Autowired
    StationMapper stationMapper;

    @GetMapping(value = "/schedule")
    public ModelAndView schedule(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("schedulePage");
        return modelAndView;
    }

    @PostMapping(value = "/schedule")
    public ModelAndView searchTrips(@RequestParam("stationFrom") String stationFromTitle,
                                    @RequestParam("stationTo") String stationToTitle,
                                    @RequestParam("dateFrom") String dateFrom,
                                    @RequestParam("dateTo") String dateTo) {
        ModelAndView modelAndView = new ModelAndView();
        StationDTO stationDTOFrom = stationService.findByStationDTOTitle(stationFromTitle);
        StationDTO stationDTOTo = stationService.findByStationDTOTitle(stationToTitle);
        Station stationFrom = stationMapper.toEntity(stationDTOFrom);
        Station stationTo = stationMapper.toEntity(stationDTOTo);

        Timestamp tmp1 = Timestamp.valueOf(LocalDateTime.parse(dateFrom));
        Timestamp tmp2 = Timestamp.valueOf(LocalDateTime.parse(dateTo));

        List<ScheduleDTO> list = scheduleService.getSchedulesByStationsAndDate(stationFrom, stationTo, tmp1, tmp2);
        modelAndView.addObject("scheduleDTOList", list);
        modelAndView.setViewName("schedulePage");
        return modelAndView;
    }

}
