package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.dto.ScheduleDTO;
import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.service.interfaces.ScheduleService;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private StationService stationService;

/*

    @GetMapping(value = "/schedule")
    public ModelAndView schedule(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("schedulePage");
        return modelAndView;
    }
*/

    @GetMapping(value = "/schedule")
    public ModelAndView searchTrips(@RequestParam(name = "stationFrom", required = false) Integer stationFromId,
                                    @RequestParam(name = "stationTo", required = false) Integer stationToId,
                                    @RequestParam("dateFrom") String dateFrom,
                                    @RequestParam("dateTo") String dateTo) {

        ModelAndView modelAndView = new ModelAndView();

        if (stationFromId == null || stationToId == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        StationDTO stationDTOFrom = stationService.getStationDTOById(stationFromId);
        StationDTO stationDTOTo = stationService.getStationDTOById(stationToId);
        Timestamp tmp1 = Timestamp.valueOf(LocalDateTime.parse(dateFrom));
        Timestamp tmp2 = Timestamp.valueOf(LocalDateTime.parse(dateTo));

        List<ScheduleDTO> list = scheduleService.getSchedulesByStationsAndDate(stationDTOFrom, stationDTOTo, tmp1, tmp2);
        modelAndView.addObject("scheduleDTOList", list);

        modelAndView.setViewName("schedulePage");
        return modelAndView;
    }


    @GetMapping(value = "/timetable")
    public ModelAndView getTimetable(@RequestParam(name = "timeTable", required = false) Integer stationId) {
        ModelAndView modelAndView = new ModelAndView();
        if (stationId == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        List<ScheduleDTO> scheduleDTOs = scheduleService.getSchedulesByStationFrom(stationService.getStationDTOById(stationId));
        modelAndView.addObject("scheduleDTOList", scheduleDTOs);
        modelAndView.setViewName("TimeTable");
        return modelAndView;
    }
}
