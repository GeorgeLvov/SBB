package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.repository.impl.TicketRepositoryImpl;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
public class TicketController {

    @Autowired
    TrainService trainService;

    @Autowired
    TicketRepositoryImpl ticketRepository;

    @GetMapping(value = "/ticket/{trainId}/{tripId}/{departureTime}/{arrivalTime}")
    public ModelAndView m(@PathVariable int trainId,
                          @PathVariable int tripId,
                          @PathVariable Timestamp departureTime,
                          @PathVariable Timestamp arrivalTime){
        ModelAndView modelAndView = new ModelAndView();
      /*  if((departureTime.getTime() - Timestamp.valueOf(LocalDateTime.now()).getTime()) < 60000
        || departureTime.getTime() < Timestamp.valueOf(LocalDateTime.now()).getTime()){
            modelAndView.addObject("msg", "Warning!");
            modelAndView.setViewName("schedulePage");
            return modelAndView;
        }*/


        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
