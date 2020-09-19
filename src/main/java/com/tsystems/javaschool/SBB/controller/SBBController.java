package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.entities.PassengerEntity;
import com.tsystems.javaschool.SBB.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SBBController {

    @Autowired
    PassengerService passengerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allPassengers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("MainPage");
        List<PassengerEntity> passengers = passengerService.getAllPassengers();
        modelAndView.addObject("passengersList", passengers);
        return modelAndView;
    }

}
