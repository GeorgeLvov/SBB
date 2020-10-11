package com.tsystems.javaschool.SBB.controller;

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


    @GetMapping(value = "/")
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<StationDTO> list = stationService.getAllStationsDTO();
        modelAndView.addObject("stationsList", list);
        modelAndView.addObject("currentDateTime", LocalDateTime.now().withSecond(0).withNano(0));
        modelAndView.setViewName("MainPage");
        return modelAndView;
    }

    @GetMapping(value = "/test")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        return modelAndView;
    }


    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("logoutmessage", "You have been logged out successfully.");
        return modelAndView;
    }

    @GetMapping(value = "/success")
    public ModelAndView userPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<StationDTO> list = stationService.getAllStationsDTO();
        modelAndView.addObject("stationsList", list);
        modelAndView.addObject("currentDateTime", LocalDateTime.now().withSecond(0).withNano(0));
        modelAndView.setViewName("success");
        return modelAndView;
    }




}
