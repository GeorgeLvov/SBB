package com.tsystems.javaschool.SBB.controller.controllers;

import com.tsystems.javaschool.SBB.dto.*;
import com.tsystems.javaschool.SBB.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;



@Controller
public class SBBController {

    @Autowired
    private StationService stationService;
    @Autowired
    private ScheduleService scheduleService;


    @ModelAttribute("stations")
    public List<StationDTO> getAllStations(){
        return stationService.getAllStationsDTO();
    }

    @ModelAttribute("currentDateTime")
    public LocalDateTime now(){
        return LocalDateTime.now().withSecond(0).withNano(0);
    }



    @GetMapping(value = "/")
    public ModelAndView getMainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("MainPage");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("logoutmessage", "You have been logged out successfully.");
        return modelAndView;
    }

    @GetMapping(value = "/success")
    public ModelAndView getUserPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Success");
        return modelAndView;
    }

    @GetMapping(value = "/schedule")
    public ModelAndView searchTrips(@RequestParam("stationFrom") Integer stationFromId,
                                    @RequestParam("stationTo") Integer stationToId,
                                    @RequestParam("dateFrom") String dateFrom,
                                    @RequestParam("dateTo") String dateTo) {

        ModelAndView modelAndView = new ModelAndView();

        StationDTO stationDTOFrom = stationService.getStationDTOById(stationFromId);
        StationDTO stationDTOTo = stationService.getStationDTOById(stationToId);
        Timestamp timeFrom = Timestamp.valueOf(LocalDateTime.parse(dateFrom));
        Timestamp timeTo = Timestamp.valueOf(LocalDateTime.parse(dateTo));

        List<ScheduleDTO> list = scheduleService.getSchedulesByStationsAndDate(stationDTOFrom, stationDTOTo, timeFrom, timeTo);

        modelAndView.addObject("scheduleDTOList", list);

        modelAndView.setViewName("SchedulePage");
        return modelAndView;
    }


    @GetMapping(value = "/timetable")
    public ModelAndView getTimetable(@RequestParam(name = "timeTable") Integer stationId) {
        ModelAndView modelAndView = new ModelAndView();
        List<ScheduleDTO> scheduleDTOsFrom = scheduleService.getSchedulesByStationFrom(stationService.getStationDTOById(stationId));
        List<ScheduleDTO> scheduleDTOsTo = scheduleService.getSchedulesByStationTo(stationService.getStationDTOById(stationId));
        modelAndView.addObject("station", stationService.getStationDTOById(stationId));
        modelAndView.addObject("scheduleDTOListFrom", scheduleDTOsFrom);
        modelAndView.addObject("scheduleDTOListTo", scheduleDTOsTo);
        modelAndView.setViewName("TimeTable");
        return modelAndView;
    }




}
