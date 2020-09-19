package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.entities.PassengerEntity;
import com.tsystems.javaschool.SBB.entities.StationEntity;
import com.tsystems.javaschool.SBB.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping(value = "/stations")
    public ModelAndView getAllStations() {
        ModelAndView modelAndView = new ModelAndView();
        List<StationEntity> stations = stationService.getAllStations();
        modelAndView.addObject("stationsList", stations);
        modelAndView.setViewName("AllStationsPage");
        return modelAndView;
    }

    @PostMapping(value = "/addStation")
    public ModelAndView addFilm(@ModelAttribute("station") StationEntity stationEntity) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/crud");
        stationService.add(stationEntity);
        return modelAndView;
    }
}
