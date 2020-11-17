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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class ManagementController {

    @Autowired
    private PassengerService passengerService;
    @Autowired
    private StationService stationService;
    @Autowired
    private StationValidator stationValidator;
    @Autowired
    private TrainService trainService;
    @Autowired
    private TrainValidator trainValidator;
    @Autowired
    private TripService tripService;


    @GetMapping(value = "/crud")
    public ModelAndView getCrudPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stationDTO", new StationDTO());
        modelAndView.addObject("trainDTO", new TrainDTO());
        modelAndView.setViewName("CrudPage");
        return modelAndView;
    }

    @PostMapping(value = "/addStation")
    public ModelAndView addStation(@ModelAttribute("stationDTO") @Valid StationDTO stationDTO,
                                   BindingResult bindingResult,
                                   @ModelAttribute("trainDTO") TrainDTO trainDTO) {
        ModelAndView modelAndView = new ModelAndView();
        stationValidator.validate(stationDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("CrudPage");
            return modelAndView;
        }

        stationService.addStation(stationDTO);
        modelAndView.setViewName("redirect:/admin/crud?station");
        return modelAndView;
    }


    @PostMapping(value = "/addTrain")
    public ModelAndView addTrain(@ModelAttribute("trainDTO") @Valid TrainDTO trainDTO,
                                 BindingResult bindingResult,
                                 @ModelAttribute("stationDTO") StationDTO stationDTO) {
        ModelAndView modelAndView = new ModelAndView();
        trainValidator.validate(trainDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("CrudPage");
            return modelAndView;
        }
        trainService.addTrain(trainDTO);
        modelAndView.setViewName("redirect:/admin/crud?train");
        return modelAndView;
    }

    @GetMapping(value = "/trainsandstations")
    public ModelAndView getAllTrainsAndStations(@ModelAttribute("stationToEdit") StationDTO stationDTO) {
        ModelAndView modelAndView = new ModelAndView();
        List<StationDTO> stations = stationService.getAllStationsDTO();
        List<TrainDTO> trains = trainService.getAllTrainsDTO();
        modelAndView.addObject("stationsList", stations);
        modelAndView.addObject("trainsList", trains);
        modelAndView.setViewName("TrainsAndStationsPage");
        return modelAndView;
    }

    @GetMapping(value = "/editstation/{id}")
    public ModelAndView editStation(@PathVariable("id") int stationId, RedirectAttributes redirectAttributes) {
        StationDTO stationDTO = stationService.getStationDTOById(stationId);
        redirectAttributes.addFlashAttribute("stationToEdit", stationDTO);
        return new ModelAndView("redirect:/admin/trainsandstations?edit=" + stationDTO.getId());
    }


    @PostMapping(value = "/editstation")
    public ModelAndView editStation(@ModelAttribute("stationToEdit")@Valid StationDTO stationDTO,
                                    BindingResult bindingResult) {
        stationValidator.validate(stationDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/admin/trainsandstations?error=" + stationDTO.getTitle());
        }
        stationService.updateStation(stationDTO);
        return new ModelAndView("redirect:/admin/trainsandstations?success");
    }


    @GetMapping(value = "/allTrips")
    public ModelAndView getAllTrips(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView();
        List<TripDTO> allTrips = tripService.getAllTrips();
        if(httpServletRequest.getParameter("lastadded") != null){
            allTrips.sort(Comparator.comparing(TripDTO::getId).reversed());
        }

        modelAndView.addObject("allTrips", allTrips);
        modelAndView.setViewName("AllTripsPage");
        return modelAndView;
    }

    @PostMapping(value = "/delayTrip/{tripId}")
    public String delayTrip(@PathVariable int tripId, @RequestParam int delay){
        tripService.delayTrip(tripId, delay);
        return "redirect:/admin/allTrips";
    }


    @GetMapping(value = "/cancelTrip/{tripId}")
    public String cancelTrip(@PathVariable int tripId){
        tripService.cancelTrip(tripId);
        return "redirect:/admin/allTrips";
    }


    @GetMapping(value = "/passengers/{trainId}/{tripId}")
    public ModelAndView getAllPassengersOnTrip(@PathVariable int trainId,
                                               @PathVariable int tripId) {
        ModelAndView modelAndView = new ModelAndView();
        List<PassengerInfo> passengers = passengerService.getAllPassengersByTrainIdAndTripId(trainId, tripId);
        TripDTO tripDTO = tripService.getTripDTOById(tripId);
        modelAndView.addObject("passengers", passengers);
        modelAndView.addObject("currentTrip", tripDTO);
        modelAndView.setViewName("Passengers");
        return modelAndView;
    }

}
