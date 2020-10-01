package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.dto.UserDTO;
import com.tsystems.javaschool.SBB.entities.User;
import com.tsystems.javaschool.SBB.mapper.UserMapper;
import com.tsystems.javaschool.SBB.service.interfaces.RoleService;
import com.tsystems.javaschool.SBB.service.interfaces.SecurityService;
import com.tsystems.javaschool.SBB.service.interfaces.UserService;
import com.tsystems.javaschool.SBB.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SBBController {

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


    @GetMapping(value = "/")
    public String mainPage() {
        return "MainPage";
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
