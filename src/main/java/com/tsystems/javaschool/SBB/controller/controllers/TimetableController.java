package com.tsystems.javaschool.SBB.controller.controllers;


import com.tsystems.javaschool.SBB.dto.StationDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimetableController {

    @GetMapping("/schedule/{stationName}")
    public StationDTO getTimetable(@PathVariable("stationName") String stationName) {
        System.out.println("Пришёл реквест!!!!!!!!!!!!!!!!!!! ---->>>>>" + stationName);
        return new StationDTO(1,"FFF");
    }
}
