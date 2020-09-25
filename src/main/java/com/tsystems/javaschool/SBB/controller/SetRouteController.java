package com.tsystems.javaschool.SBB.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SetRouteController {

    @GetMapping(value = "/setroute")
    public ModelAndView route(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("SetRoutePage");
        return modelAndView;
    }

    @PostMapping(value = "/setroute")
    public ModelAndView setRoute(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("SetRoutePage");
        return modelAndView;
    }
}
