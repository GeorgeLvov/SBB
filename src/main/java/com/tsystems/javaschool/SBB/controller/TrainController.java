package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import com.tsystems.javaschool.SBB.validator.TrainValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping(value = "admin/trains")
    public ModelAndView getAllTrains() {
        ModelAndView modelAndView = new ModelAndView();
        List<TrainDTO> trains = trainService.getAllTrainsDTO();
        modelAndView.addObject("trainsList", trains);
        modelAndView.setViewName("AllTrainsPage");
        return modelAndView;
    }


}