package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping(value = "/trains")
    public ModelAndView getAllTrains() {
        ModelAndView modelAndView = new ModelAndView();
        List<TrainDTO> trains = trainService.getAllTrainsDTO();
        modelAndView.addObject("trainsList", trains);
        modelAndView.setViewName("AllTrainsPage");
        return modelAndView;
    }

    @PostMapping(value = "/addTrain")
    public ModelAndView addFilm(@ModelAttribute("train") TrainDTO trainDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/crud");
        trainService.add(trainDTO);
        return modelAndView;
    }
}
