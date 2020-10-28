package com.tsystems.javaschool.SBB.controller.controllers;

import com.tsystems.javaschool.SBB.dto.*;
import com.tsystems.javaschool.SBB.service.interfaces.*;
import com.tsystems.javaschool.SBB.validator.StationValidator;
import com.tsystems.javaschool.SBB.validator.TrainValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class CrudController {

    @Autowired
    private PassengerService passengerService;
    @Autowired
    private StationService stationService;
    @Autowired
    private TrainService trainService;
    @Autowired
    private StationValidator stationValidator;
    @Autowired
    private TrainValidator trainValidator;
    @Autowired
    private TripService tripService;


    @GetMapping(value = "/crud")
    public ModelAndView addTrainOrStation() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stationDTO", new StationDTO());
        modelAndView.addObject("trainDTO", new TrainDTO());
        modelAndView.setViewName("CrudPage");
        return modelAndView;
    }

    @PostMapping(value = "/addStation")
    public ModelAndView addStation(@ModelAttribute("stationDTO")@Valid StationDTO stationDTO,
                                   BindingResult bindingResult,
                                   @ModelAttribute("trainDTO") TrainDTO trainDTO
    ) {
        ModelAndView modelAndView = new ModelAndView();
        stationValidator.validate(stationDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("CrudPage");
            return modelAndView;
        }

        stationService.addStation(stationDTO);
        modelAndView.setViewName("redirect:/admin/crud");
        return modelAndView;
    }

    @PostMapping(value = "/addTrain")
    public ModelAndView addTrain(@ModelAttribute("trainDTO") TrainDTO trainDTO,
                                 BindingResult bindingResult,
                                 @ModelAttribute("stationDTO") StationDTO stationDTO) {
        ModelAndView modelAndView = new ModelAndView();
        trainValidator.validate(trainDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("CrudPage");
            return modelAndView;
        }
        trainService.addTrain(trainDTO);
        modelAndView.setViewName("redirect:/admin/crud");
        return modelAndView;
    }

    @GetMapping(value = "/trains")
    public ModelAndView getAllTrains() {
        ModelAndView modelAndView = new ModelAndView();
        List<TrainDTO> trains = trainService.getAllTrainsDTO();
        modelAndView.addObject("trainsList", trains);
        modelAndView.setViewName("AllTrainsPage");
        return modelAndView;
    }

    @GetMapping(value = "/stations")
    public ModelAndView getAllStations() {
        ModelAndView modelAndView = new ModelAndView();
        List<StationDTO> stations = stationService.getAllStationsDTO();
        modelAndView.addObject("stationsList", stations);
        modelAndView.setViewName("AllStationsPage");
        return modelAndView;
    }

    @GetMapping(value = "/allTrips")
    public ModelAndView getAllAllTrips() {
        ModelAndView modelAndView = new ModelAndView();
        List<TripDTO> allTrips = tripService.getAllTrips();
        modelAndView.addObject("allTrips", allTrips);
        modelAndView.setViewName("AllTripsPage");
        return modelAndView;
    }

    @GetMapping(value = "/cancelTrip/{tripId}")
    public String cancelTrip(@PathVariable int tripId){
        tripService.cancelTrip(tripId);
        return "redirect:/admin/allTrips";
    }

    @PostMapping(value = "/delayTrip/{tripId}")
    public String delayTrip(@PathVariable int tripId, @RequestParam int delay){
        tripService.delayTrip(tripId, delay);
        return "redirect:/admin/allTrips";
    }

    @GetMapping(value = "/passengers/{trainId}/{tripId}")
    public ModelAndView getAllPassengersOnTrip(@PathVariable int trainId,
                                               @PathVariable int tripId) {
        ModelAndView modelAndView = new ModelAndView();
        List<PassengerInfo> passengers = passengerService.getAllPassengersByTrainIdAndTripId(trainId, tripId);
        modelAndView.addObject("passengers", passengers);
        modelAndView.setViewName("Passengers");
        return modelAndView;
    }

}
