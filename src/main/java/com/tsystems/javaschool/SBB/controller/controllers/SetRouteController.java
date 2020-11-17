package com.tsystems.javaschool.SBB.controller.controllers;

import com.tsystems.javaschool.SBB.dto.RouteDTO;
import com.tsystems.javaschool.SBB.dto.RouteContainer;
import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.TrainDTO;

import com.tsystems.javaschool.SBB.service.interfaces.RouteContainerService;
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

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/setroute")
    public ModelAndView setRoute(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if(request.getParameter("start") != null){
            containerService.truncateContainer();
        }
        modelAndView.addObject("routeDTO", new RouteDTO());
        modelAndView.addObject("resultRouteDTO", routeContainer);
        modelAndView.setViewName("SetRoutePage");
        return modelAndView;
    }

    @PostMapping("/trainselect")
    public ModelAndView selectTrain(@Valid @ModelAttribute("routeDTO") RouteDTO routeDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(routeDTO);
        startRouteValidator.validate(routeDTO, bindingResult);
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error", "error");
            modelAndView.setViewName("SetRoutePage");
            return modelAndView;
        }
        containerService.setInitialInfo(routeDTO);
        modelAndView.setViewName("redirect:/admin/setroute");
        return modelAndView;
    }

    @PostMapping("/setroute")
    public ModelAndView setRoute(@ModelAttribute("routeDTO") RouteDTO routeDTO, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        routeValidator.validate(routeDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("resultRouteDTO", routeContainer);
            modelAndView.setViewName("SetRoutePage");
            return modelAndView;
        }

        if (CollectionUtils.isEmpty(routeContainer.getSideStations())) {
            containerService.setSegmentsInfo(routeDTO);
        } else {
            containerService.updateSegmentsInfo(routeDTO);
        }

        modelAndView.setViewName("redirect:/admin/setroute");
        return modelAndView;
    }

    @GetMapping(value = "/deleteLast")
    public String deleteLastChange() {
        if (CollectionUtils.isNotEmpty(routeContainer.getSideStations())
                && CollectionUtils.isNotEmpty(routeContainer.getSideArrivalTimes())) {
            containerService.deleteLastChange();
            return "redirect:/admin/setroute";
        }
        return "redirect:/admin/setroute?start";
    }

    @GetMapping("/createtrip")
    public RedirectView createTrip(RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView("/admin/management",true);
        scheduleService.createSchedulesAndTrip();
        redirectAttributes.addFlashAttribute("successMessage", "Trip was created.");
        containerService.truncateContainer();
        return redirectView;
    }

}
