package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.mapper.UserMapper;
import com.tsystems.javaschool.SBB.service.interfaces.RoleService;
import com.tsystems.javaschool.SBB.service.interfaces.SecurityService;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import com.tsystems.javaschool.SBB.service.interfaces.UserService;
import com.tsystems.javaschool.SBB.validator.StationValidator;
import com.tsystems.javaschool.SBB.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class StationController {

    @Autowired
    private StationService stationService;
    @Autowired
    UserService userService;
    @Autowired
    SecurityService securityService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleService roleService;
    @Autowired
    private StationValidator stationValidator;


    @GetMapping(value = "/stations")
    public ModelAndView getAllStations() {
        ModelAndView modelAndView = new ModelAndView();
        List<StationDTO> stations = stationService.getAllStationsDTO();
        modelAndView.addObject("stationsList", stations);
        modelAndView.setViewName("AllStationsPage");
        return modelAndView;
    }

    @PostMapping(value = "/admin/crud")
    public ModelAndView addStation(@ModelAttribute("stationDTO") StationDTO stationDTO, BindingResult bindingResult) {
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

}
