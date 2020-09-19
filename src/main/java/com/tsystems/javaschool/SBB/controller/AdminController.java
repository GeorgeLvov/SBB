package com.tsystems.javaschool.SBB.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @GetMapping(value = "/admin")
    public ModelAndView adminMainPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminMainPage");
        return modelAndView;
    }

    @GetMapping(value = "/crud")
    public ModelAndView addTrainOrStation(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("crudPage");
        return modelAndView;
    }

}
