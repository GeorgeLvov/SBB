package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.TicketDTOContainer;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import com.tsystems.javaschool.SBB.service.interfaces.TicketService;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import com.tsystems.javaschool.SBB.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

@Service
public class TicketDTOContainerService {

    @Autowired
    TicketDTOContainer ticketDTOContainer;
    @Autowired
    TicketService ticketService;
    @Autowired
    StationService stationService;
    @Autowired
    TrainService trainService;
    @Autowired
    UserService userService;


    public boolean checkTrip(Map<String, String> allRequestParams) {

        int trainId = Integer.parseInt(allRequestParams.get("trainId").trim());
        int tripId = Integer.parseInt(allRequestParams.get("tripId").trim());
        int stationFromId = Integer.parseInt(allRequestParams.get("stF").trim());
        int stationToId = Integer.parseInt(allRequestParams.get("stT").trim());
        Timestamp departureTime = Timestamp.valueOf(allRequestParams.get("departureTime"));
        Timestamp arrivalTime = Timestamp.valueOf(allRequestParams.get("arrivalTime"));

        if (ticketService.isTimeValid(departureTime)
                && !ticketService.isTrainFull(departureTime, arrivalTime, trainId, tripId)) {
            ticketDTOContainer.setTrainDTO(trainService.getTrainDTOById(trainId));
            ticketDTOContainer.setTripId(tripId);
            ticketDTOContainer.setStationFromDTO(stationService.getStationDTOById(stationFromId));
            ticketDTOContainer.setStationToDTO(stationService.getStationDTOById(stationToId));
            ticketDTOContainer.setDepartureTime(departureTime);
            ticketDTOContainer.setArrivalTime(arrivalTime);
            ticketDTOContainer.setValid(true);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            if (!username.equals("anonymousUser")) {
                ticketDTOContainer.setUserDTO(userService.findByUsername(auth.getName()));
            }

            return true;
        }
        return false;
    }
}
