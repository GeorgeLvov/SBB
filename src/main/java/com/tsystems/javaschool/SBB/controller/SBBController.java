package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.UserDTO;
import com.tsystems.javaschool.SBB.entities.User;
import com.tsystems.javaschool.SBB.mapper.UserMapper;
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

import java.time.LocalDateTime;
import java.util.List;


@Controller
public class SBBController {

    @Autowired
    StationService stationService;
    @Autowired
    SecurityService securityService;
    @Autowired
    UserValidator userValidator;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;


    @GetMapping(value = "/")
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<StationDTO> list = stationService.getAllStationsDTO();
        modelAndView.addObject("stationsList", list);
        modelAndView.addObject("currentDateTime", LocalDateTime.now().withSecond(0).withNano(0));
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
        modelAndView.addObject("currentDateTime", LocalDateTime.now().withSecond(0).withNano(0));
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
        User userForm = userMapper.toEntity(userDTO);
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registrationPage");
            return modelAndView;
        }
        userDTO.setRoleDTO(roleService.getRoleDTOById(1));
        userService.add(userDTO);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        modelAndView.setViewName("success");
        return modelAndView;
    }



}
