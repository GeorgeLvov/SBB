package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.UserDTO;
import com.tsystems.javaschool.SBB.entities.Ticket;
import com.tsystems.javaschool.SBB.repository.impl.TicketRepositoryImpl;
import com.tsystems.javaschool.SBB.service.interfaces.RoleService;
import com.tsystems.javaschool.SBB.service.interfaces.SecurityService;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import com.tsystems.javaschool.SBB.service.interfaces.UserService;
import com.tsystems.javaschool.SBB.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Controller
public class SBBController {

    @Autowired
    private StationService stationService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    private final LocalDateTime currentTime = LocalDateTime.now().withSecond(0).withNano(0);

    @GetMapping(value = "/")
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<StationDTO> list = stationService.getAllStationsDTO();
        modelAndView.addObject("stationsList", list);
        modelAndView.addObject("currentDateTime", currentTime);
        modelAndView.setViewName("MainPage");
        return modelAndView;
    }


    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("logoutmessage", "You have been logged out successfully.");
        return modelAndView;
    }

    @GetMapping(value = "/success")
    public ModelAndView userPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<StationDTO> list = stationService.getAllStationsDTO();
        modelAndView.addObject("stationsList", list);
        modelAndView.addObject("currentDateTime", currentTime);
        modelAndView.setViewName("success");
        return modelAndView;
    }


    @GetMapping("/registration")
    public ModelAndView registryG() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registrationPage");
        modelAndView.addObject("userForm", new UserDTO());
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView registry(@ModelAttribute("userForm") UserDTO userDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registrationPage");
            return modelAndView;
        }
        userDTO.setRoleDTO(roleService.getRoleDTOById(1));
        userService.add(userDTO);

        securityService.autoLogin(userDTO.getUsername(), userDTO.getConfirmPassword());

        modelAndView.setViewName("redirect:/success");
        return modelAndView;
    }

}
