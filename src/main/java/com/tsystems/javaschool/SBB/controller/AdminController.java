package com.tsystems.javaschool.SBB.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class AdminController {

    @GetMapping(value = "/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView adminMainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminMainPage");
        return modelAndView;
    }

    @GetMapping(value = "/crud")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addTrainOrStation() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("crudPage");
        return modelAndView;
    }

}
