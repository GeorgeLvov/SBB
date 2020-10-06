package com.tsystems.javaschool.SBB.controller;


import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class SetRouteController {
    @Autowired
    TrainService trainService;
    @Autowired
    StationService stationService;
    private final LocalDateTime currentTime = LocalDateTime.now().withSecond(0).withNano(0);

    @GetMapping(value = "/setroute")
    public ModelAndView route(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("SetRoutePage");

        List<TrainDTO> trainDTOs = trainService.getAllTrainsDTO();
        modelAndView.addObject("trainsList", trainDTOs);

        List<StationDTO> stationDTOs = stationService.getAllStationsDTO();
        modelAndView.addObject("stationsList",stationDTOs);

        modelAndView.addObject("currentTime", currentTime);

       // modelAndView.addObject("schInfoDTO",new ScheduleInfoDTO());

        return modelAndView;
    }

  /*  @PostMapping(value = "/setroute")
    public ModelAndView schedule(@ModelAttribute("schInfoDTO") ScheduleInfoDTO scheduleInfoDTO,
                                 BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(scheduleInfoDTO);

        modelAndView.setViewName("redirect:/setroute");
        return modelAndView;
    }*/

}
