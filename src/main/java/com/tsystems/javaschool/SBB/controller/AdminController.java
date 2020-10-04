package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.UserDTO;
import com.tsystems.javaschool.SBB.entities.User;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

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


    @GetMapping(value = "/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView adminMainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminMainPage");
        return modelAndView;
    }

    @GetMapping(value = "/admin/crud")
    public ModelAndView addTrainOrStation() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stationDTO", new StationDTO());
        modelAndView.setViewName("crudPage");
        return modelAndView;
    }


    @GetMapping("/admin/addemployee")
    public ModelAndView registryG(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registrationPage");
        modelAndView.addObject("userForm", new UserDTO());
        return modelAndView;
    }


    @PostMapping(value = "/admin/addemployee")
    public ModelAndView registry(@ModelAttribute("userForm") UserDTO userDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registrationPage");
            return modelAndView;
        }
        userDTO.setRoleDTO(roleService.getRoleDTOById(2));
        userService.add(userDTO);
        modelAndView.addObject("message", "New employee account created!");
        modelAndView.addObject("usrname", userDTO.getUsername());
        modelAndView.addObject("passw", userDTO.getPassword());
        modelAndView.setViewName("adminMainPage");
        return modelAndView;
    }

}
