package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
        List<StationDTO> stations = stationService.getAllStationsDTO();
        modelAndView.addObject("stationsList", stations);
        modelAndView.setViewName("AllStationsPage");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/addStation")
    public ModelAndView addStation(@ModelAttribute("stationDTO") StationDTO stationDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/crud");
        stationService.add(stationDTO);
        return modelAndView;
    }
}
