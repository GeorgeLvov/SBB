package com.tsystems.javaschool.SBB.controller.controllers;

import com.tsystems.javaschool.SBB.dto.RouteDTO;
import com.tsystems.javaschool.SBB.dto.RouteContainer;
import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.service.impl.RouteContainerService;
import com.tsystems.javaschool.SBB.service.interfaces.ScheduleService;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import com.tsystems.javaschool.SBB.utils.CollectionUtils;
import com.tsystems.javaschool.SBB.validator.RouteValidator;
import com.tsystems.javaschool.SBB.validator.StartRouteValidator;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class SetRouteController{

    @Autowired
    private StartRouteValidator startRouteValidator;
    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private TrainService trainService;
    @Autowired
    private StationService stationService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private RouteContainer routeContainer;
    @Autowired
    private RouteContainerService containerService;


    @ModelAttribute("stationsList")
    public List<StationDTO> getAllStations(){
        return stationService.getAllStationsDTO();
    }

    @ModelAttribute("trainsList")
    public List<TrainDTO> getAllTrains(){
        return trainService.getAllTrainsDTO();
    }

    @GetMapping("/trainselect")
    public ModelAndView selectTrain() {
        ModelAndView modelAndView = new ModelAndView();
        containerService.truncate();
        modelAndView.addObject("routeDTO", new RouteDTO());
        modelAndView.setViewName("StartSetRoutePage");
        return modelAndView;
    }

    @PostMapping("/trainselect")
    public ModelAndView selectTrain(@Valid @ModelAttribute("routeDTO") RouteDTO routeDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        startRouteValidator.validate(routeDTO, bindingResult);
        if(bindingResult.hasErrors()){
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
        modelAndView.addObject("resultRouteDTO", routeContainer);
        modelAndView.addObject("routeDTO", new RouteDTO());
        modelAndView.setViewName("SetRoutePage");
        return modelAndView;
    }


    @PostMapping("/setRoute")
    public ModelAndView setRoute(@ModelAttribute("routeDTO") RouteDTO routeDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        routeValidator.validate(routeDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("resultRouteDTO", routeContainer);
            modelAndView.setViewName("SetRoutePage");
            return modelAndView;
        }
        if (CollectionUtils.isNotEmpty(routeContainer.getSideStations())) {
            containerService.updateListFields(routeDTO);
        } else containerService.setFields(routeDTO, false);

        modelAndView.setViewName("redirect:/admin/setRoute");
        return modelAndView;
    }


    @GetMapping(value = "/deleteLast")
    public String deleteLastChange() {
        if (CollectionUtils.isNotEmpty(routeContainer.getSideStations())
                && CollectionUtils.isNotEmpty(routeContainer.getSideArrivalTimes())) {
            containerService.deleteLastChange();
            return "redirect:/admin/setRoute";
        }
        return "redirect:/admin/trainselect";

    }

    @GetMapping("/createtrip")
    public RedirectView createTrip(RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView("/admin/management",true);
        scheduleService.createTrip();
        redirectAttributes.addFlashAttribute("successMessage",
                "Trip was created!" + "Show it in dropdown tab \"Show\" -> \"Show all trips\" ");
        containerService.truncate();
        return redirectView;
    }


}
