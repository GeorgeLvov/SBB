package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import com.tsystems.javaschool.SBB.validator.StationValidator;
import com.tsystems.javaschool.SBB.validator.TrainValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CrudController {

    @Autowired
    private StationService stationService;
    @Autowired
    private StationValidator stationValidator;
    @Autowired
    private TrainService trainService;
    @Autowired
    private TrainValidator trainValidator;

    @GetMapping(value = "/admin/crud")
    public ModelAndView addTrainOrStation() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stationDTO", new StationDTO());
        modelAndView.addObject("trainDTO", new TrainDTO());
        modelAndView.setViewName("crudPage");
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
            modelAndView.setViewName("crudPage");
            return modelAndView;
        }

        stationService.add(stationDTO);
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
            modelAndView.setViewName("crudPage");
            return modelAndView;
        }
        trainService.add(trainDTO);
        modelAndView.setViewName("redirect:/admin/crud");
        return modelAndView;
    }

    @GetMapping(value = "admin/trains")
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
}
