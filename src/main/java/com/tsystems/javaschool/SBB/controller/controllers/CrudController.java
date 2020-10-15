package com.tsystems.javaschool.SBB.controller.controllers;

import com.tsystems.javaschool.SBB.dto.PassengerInfoDTO;
import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.dto.TripInfoDTO;
import com.tsystems.javaschool.SBB.service.interfaces.PassengerService;
import com.tsystems.javaschool.SBB.service.interfaces.ScheduleService;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import com.tsystems.javaschool.SBB.validator.StationValidator;
import com.tsystems.javaschool.SBB.validator.TrainValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;


@Controller
public class CrudController {

    @Autowired
    private PassengerService passengerService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private StationService stationService;
    @Autowired
    private TrainService trainService;
    @Autowired
    private StationValidator stationValidator;
    @Autowired
    private TrainValidator trainValidator;



    @GetMapping(value = "/admin/crud")
    public ModelAndView addTrainOrStation() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stationDTO", new StationDTO());
        modelAndView.addObject("trainDTO", new TrainDTO());
        modelAndView.setViewName("CrudPage");
        return modelAndView;
    }

    @PostMapping(value = "/admin/addStation")
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

    @PostMapping(value = "/admin/addTrain")
    public ModelAndView addTrain(@ModelAttribute("trainDTO") TrainDTO trainDTO,
                                 BindingResult bindingResult,
                                 @ModelAttribute("stationDTO") StationDTO stationDTO
    ) {
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

    @GetMapping(value = "/admin/trains")
    public ModelAndView getAllTrains() {
        ModelAndView modelAndView = new ModelAndView();
        List<TrainDTO> trains = trainService.getAllTrainsDTO();
        modelAndView.addObject("trainsList", trains);
        modelAndView.setViewName("AllTrainsPage");
        return modelAndView;
    }

    @GetMapping(value = "/admin/stations")
    public ModelAndView getAllStations() {
        ModelAndView modelAndView = new ModelAndView();
        List<StationDTO> stations = stationService.getAllStationsDTO();
        modelAndView.addObject("stationsList", stations);
        modelAndView.setViewName("AllStationsPage");
        return modelAndView;
    }

   @GetMapping(value = "/admin/trainsAndRoutes")
   public ModelAndView getAllTrips() {
       ModelAndView modelAndView = new ModelAndView();
       List<List<TripInfoDTO>> tripsList = scheduleService.getAllTrips();
       modelAndView.addObject("allTrips", tripsList);
       modelAndView.setViewName("AllTripsPage");
       return modelAndView;
   }


    @GetMapping(value = "/passengers/{trainId}/{tripId}/{stationFrom}/{stationTo}/{departureTime}/{arrivalTime}")
    public ModelAndView getAllPassengersOnTrip(@PathVariable int trainId,
                                               @PathVariable int tripId,
                                               @PathVariable String stationFrom,
                                               @PathVariable String stationTo,
                                               @PathVariable Timestamp departureTime,
                                               @PathVariable Timestamp arrivalTime) {
        ModelAndView modelAndView = new ModelAndView();
        List<PassengerInfoDTO> passengers = passengerService.getAllPassengersByTrainIdAndTripId(trainId,tripId);
        modelAndView.addObject("passengers", passengers);
        modelAndView.addObject("stationFrom", stationFrom);
        modelAndView.addObject("stationTo", stationTo);
        modelAndView.addObject("departureTime", departureTime);
        modelAndView.addObject("arrivalTime", arrivalTime);
        modelAndView.setViewName("Passengers");
        return modelAndView;
    }


}
