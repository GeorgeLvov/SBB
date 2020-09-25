package com.tsystems.javaschool.SBB.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SBBController {

    @GetMapping(value = "/")
    public String mainPage() {
        return "MainPage";
    }

    @GetMapping(value = "/user")
    public String userPage() {
        return "MainPage";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

}
