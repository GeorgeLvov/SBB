package com.tsystems.javaschool.SBB.controller.controllers;


import com.tsystems.javaschool.SBB.dto.*;
import com.tsystems.javaschool.SBB.repository.interfaces.TripRepository;
import com.tsystems.javaschool.SBB.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Arrays;
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



/*    @GetMapping(value = "/send")
    public ModelAndView send() {
        ModelAndView modelAndView = new ModelAndView();
        messageSender.sendMessage("Message!");
        modelAndView.setViewName("Success");
        return modelAndView;
    }*/

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

        List<ScheduleDTO> list = scheduleService
                .getSchedulesByStationsAndDate(stationFromId, stationToId, dateFrom, dateTo);

        modelAndView.addObject("scheduleDTOList", list);
        modelAndView.setViewName("SchedulePage");
        return modelAndView;
    }


    @GetMapping(value = "/timetable")
    public ModelAndView getTimetable(@RequestParam(name = "timeTable") Integer stationId) {

        ModelAndView modelAndView = new ModelAndView();

        StationDTO stationDTO = stationService.getStationDTOById(stationId);

        List<ScheduleDTO> scheduleDTOsFrom = scheduleService.getSchedulesByStationFrom(stationDTO);
        List<ScheduleDTO> scheduleDTOsTo = scheduleService.getSchedulesByStationTo(stationDTO);

        modelAndView.addObject("station", stationDTO);
        modelAndView.addObject("scheduleDTOListFrom", scheduleDTOsFrom);
        modelAndView.addObject("scheduleDTOListTo", scheduleDTOsTo);
        modelAndView.setViewName("TimeTable");
        return modelAndView;
    }

}
