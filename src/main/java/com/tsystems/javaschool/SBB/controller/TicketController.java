package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.service.interfaces.*;
import com.tsystems.javaschool.SBB.validator.PassengerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

@Controller
public class TicketController {

    @Autowired
    StationService stationService;
    @Autowired
    TrainService trainService;
    @Autowired
    TicketService ticketService;
    @Autowired
    TripService tripService;
    @Autowired
    UserService userService;
    @Autowired
    PassengerService passengerService;
    @Autowired
    PassengerValidator passengerValidator;


    private TicketDTO ticketDTO = new TicketDTO();

    @GetMapping(value = "/checkin")
    public ModelAndView checkIn(@RequestParam Map<String, String> allRequestParams) {

        ModelAndView modelAndView = new ModelAndView();

        int trainId = Integer.parseInt(allRequestParams.get("trainId").trim());
        int tripId = Integer.parseInt(allRequestParams.get("tripId").trim());
        int stationFromId = Integer.parseInt(allRequestParams.get("stF").trim());
        int stationToId = Integer.parseInt(allRequestParams.get("stT").trim());
        String departureTime = allRequestParams.get("departureTime");
        String arrivalTime = allRequestParams.get("arrivalTime");

        System.out.println(trainId);
        System.out.println(tripId);
        System.out.println(stationFromId);
        System.out.println(stationToId);
        System.out.println(departureTime);
        System.out.println(arrivalTime);

        if (ticketService.isTimeValid(departureTime)
                && !ticketService.isTrainFull(departureTime, arrivalTime, trainId, tripId)) {

            ticketDTO.setTrainDTO(trainService.getTrainDTOById(trainId));
            ticketDTO.setTripDTO(tripService.getTripById(tripId));
            ticketDTO.setStationFromDTO(stationService.getStationDTOById(stationFromId));
            ticketDTO.setStationToDTO(stationService.getStationDTOById(stationToId));
            ticketDTO.setDepartureTime(Timestamp.valueOf(departureTime));
            ticketDTO.setArrivalTime(Timestamp.valueOf(arrivalTime));
            ticketDTO.setValid(true);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            if (!username.equals("anonymousUser")) {
                ticketDTO.setUserDTO(userService.findByUsername(auth.getName()));
            }

            modelAndView.addObject("passForm", new PassengerDTO());
            modelAndView.setViewName("passengerCheckIn");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }


    @PostMapping("/checkin")
    public ModelAndView checkInPassenger(@ModelAttribute("passForm") PassengerDTO passengerDTO, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();

        passengerValidator.setTicketDTO(ticketDTO);
        passengerValidator.validate(passengerDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("passengerCheckIn");
            return modelAndView;
        }

        String firstName = passengerDTO.getFirstName();
        String lastName = passengerDTO.getLastName();
        Date birthDate = passengerDTO.getBirthDate();

        PassengerDTO existingPassenger = passengerService.findPassengerByPersonalData(firstName, lastName, birthDate);

        if (existingPassenger == null) {

            passengerService.add(passengerDTO);
            PassengerDTO newPassenger = passengerService.findPassengerByPersonalData(firstName, lastName, birthDate);
            ticketDTO.setPassengerDTO(newPassenger);

        } else ticketDTO.setPassengerDTO(existingPassenger);

        ticketService.add(ticketDTO);

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}

