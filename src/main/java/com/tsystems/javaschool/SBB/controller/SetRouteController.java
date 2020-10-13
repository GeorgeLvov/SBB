package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.dto.RouteDTO;
import com.tsystems.javaschool.SBB.dto.RouteDTOContainer;
import com.tsystems.javaschool.SBB.service.impl.RouteDTOContainerService;
import com.tsystems.javaschool.SBB.service.interfaces.ScheduleService;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import com.tsystems.javaschool.SBB.validator.RouteValidator;
import com.tsystems.javaschool.SBB.validator.StartRouteValidator;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class SetRouteController{

    @Autowired
    StartRouteValidator startRouteValidator;
    @Autowired
    RouteValidator routeValidator;
    @Autowired
    TrainService trainService;
    @Autowired
    StationService stationService;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    RouteDTOContainer routeDTOContainer;
    @Autowired
    RouteDTOContainerService containerService;



    @GetMapping("/trainselect")
    public ModelAndView selectTrain() {
        ModelAndView modelAndView = new ModelAndView();
        containerService.truncate();
        modelAndView.addObject("trainsList", trainService.getAllTrainsDTO());
        modelAndView.addObject("stationList", stationService.getAllStationsDTO());
        modelAndView.addObject("routeDTO", new RouteDTO());
        modelAndView.setViewName("StartSetRoutePage");
        return modelAndView;
    }

    @PostMapping("/trainselect")
    public ModelAndView selectTrain(@Valid @ModelAttribute("routeDTO") RouteDTO routeDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        startRouteValidator.validate(routeDTO, bindingResult);
        if(bindingResult.hasErrors()){
            modelAndView.addObject("trainsList", trainService.getAllTrainsDTO());
            modelAndView.addObject("stationList", stationService.getAllStationsDTO());
            modelAndView.setViewName("StartSetRoutePage");
            return modelAndView;
        }
        containerService.setFields(routeDTO, true);
        modelAndView.setViewName("redirect:/admin/setRoute");
        return modelAndView;
    }


    @GetMapping("/setRoute")
    public ModelAndView setRoute() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stationList", stationService.getAllStationsDTO());
        modelAndView.addObject("resultRouteDTO", routeDTOContainer);
        modelAndView.addObject("routeDTO", new RouteDTO());
        modelAndView.setViewName("SetRoutePage");
        return modelAndView;
    }


    @PostMapping("/setRoute")
    public ModelAndView setRoute(@ModelAttribute("routeDTO") RouteDTO routeDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        routeValidator.validate(routeDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("stationList", stationService.getAllStationsDTO());
            modelAndView.addObject("resultRouteDTO", routeDTOContainer);
            modelAndView.setViewName("SetRoutePage");
            return modelAndView;
        }

        if (routeDTOContainer.getSideStations() != null) {
            containerService.updateListFields(routeDTO);
        } else containerService.setFields(routeDTO, false);

        modelAndView.setViewName("redirect:/admin/setRoute");
        return modelAndView;
    }


    @GetMapping(value = "/deleteLast")
    public ModelAndView deleteLastChange() {
        ModelAndView modelAndView = new ModelAndView();
        if (routeDTOContainer.getSideStations() != null && !routeDTOContainer.getSideStations().isEmpty()
                && routeDTOContainer.getSideArrivalTimes() != null && !routeDTOContainer.getSideArrivalTimes().isEmpty()) {
            containerService.deleteLastChange();
            modelAndView.setViewName("redirect:/admin/setRoute");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/admin/trainselect");
        return modelAndView;
    }

    @GetMapping("/createtrip")
    public ModelAndView createTrip() {
        ModelAndView modelAndView = new ModelAndView();
        scheduleService.createTrip();
        containerService.truncate();
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }


}
