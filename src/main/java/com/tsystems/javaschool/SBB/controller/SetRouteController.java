package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.dto.RouteDTO;
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

    private RouteDTO resultRouteDTO;

    @GetMapping("/trainselect")
    public ModelAndView selectTrain() {
        ModelAndView modelAndView = new ModelAndView();
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
        resultRouteDTO = routeDTO;
        modelAndView.setViewName("redirect:/admin/setRoute");
        return modelAndView;
    }


    @GetMapping("/setRoute")
    public ModelAndView setRoute() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stationList", stationService.getAllStationsDTO());
        modelAndView.addObject("resultRouteDTO", resultRouteDTO);
        modelAndView.addObject("routeDTO", new RouteDTO());
        modelAndView.setViewName("SetRoutePage");
        return modelAndView;
    }


    @PostMapping("/setRoute")
    public ModelAndView setRoute(@ModelAttribute("routeDTO") RouteDTO routeDTO, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();

        routeDTO.setTrainName(resultRouteDTO.getTrainName());
        routeDTO.setDepartureStationName(resultRouteDTO.getDepartureStationName());
        routeDTO.setDepartureDate(resultRouteDTO.getDepartureDate());
        routeDTO.setDeclaredArrivalDate(resultRouteDTO.getDeclaredArrivalDate());

        routeValidator.setResultRouteDTO(resultRouteDTO);
        routeValidator.validate(routeDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("stationList", stationService.getAllStationsDTO());
            modelAndView.addObject("resultRouteDTO", resultRouteDTO);
            modelAndView.setViewName("SetRoutePage");
            return modelAndView;
        }

        if(resultRouteDTO.getSideStations() != null){
            resultRouteDTO.getSideStations().addAll(routeDTO.getSideStations());
            resultRouteDTO.getSideArrivalTimes().addAll(routeDTO.getSideArrivalTimes());
            resultRouteDTO.getStops().addAll(routeDTO.getStops());
        } else resultRouteDTO = routeDTO;

        modelAndView.setViewName("redirect:/admin/setRoute");
        return modelAndView;
    }


    @GetMapping(value = "/deleteLast")
    public ModelAndView deleteLastChange() {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(resultRouteDTO.getSideArrivalTimes());
        if (resultRouteDTO.getSideStations() != null && !resultRouteDTO.getSideStations().isEmpty()
                && resultRouteDTO.getSideArrivalTimes() != null && !resultRouteDTO.getSideArrivalTimes().isEmpty()) {
            resultRouteDTO.getSideStations().remove(resultRouteDTO.getSideStations().size() - 1);
            resultRouteDTO.getSideArrivalTimes().remove(resultRouteDTO.getSideArrivalTimes().size() - 1);
            resultRouteDTO.getStops().remove(resultRouteDTO.getStops().size() - 1);
            modelAndView.setViewName("redirect:/admin/setRoute");
            return modelAndView;
        } else modelAndView.setViewName("redirect:/admin/trainselect");
        return modelAndView;
    }

    @GetMapping("/createtrip")
    public ModelAndView createTrip() {
        ModelAndView modelAndView = new ModelAndView();
        scheduleService.createTrip(resultRouteDTO);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }


}
