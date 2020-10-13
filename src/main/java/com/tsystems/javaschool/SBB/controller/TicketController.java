package com.tsystems.javaschool.SBB.controller;

import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTOContainer;
import com.tsystems.javaschool.SBB.dto.TicketInfoDTO;
import com.tsystems.javaschool.SBB.service.impl.TicketDTOContainerService;
import com.tsystems.javaschool.SBB.service.interfaces.*;
import com.tsystems.javaschool.SBB.utils.TicketPDFExporter;
import com.tsystems.javaschool.SBB.validator.PassengerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class TicketController {

    @Autowired
    TicketService ticketService;
    @Autowired
    UserService userService;
    @Autowired
    PassengerService passengerService;
    @Autowired
    PassengerValidator passengerValidator;
    @Autowired
    TicketDTOContainer ticketDTOContainer;
    @Autowired
    TicketDTOContainerService containerService;


    @GetMapping(value = "/checkin")
    public ModelAndView checkIn(@RequestParam Map<String, String> allRequestParams) {
        ModelAndView modelAndView = new ModelAndView();

        if (containerService.checkTrip(allRequestParams)) {
            modelAndView.addObject("passForm", new PassengerDTO());
            modelAndView.setViewName("passengerCheckIn");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }


    @PostMapping("/checkin")
    public ModelAndView checkInPassenger(@ModelAttribute("passForm") PassengerDTO passengerDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        passengerValidator.validate(passengerDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("passengerCheckIn");
            return modelAndView;
        }

        if(ticketService.isTimeValid(ticketDTOContainer.getDepartureTime())
                && !ticketService.isTrainFull(ticketDTOContainer.getDepartureTime(), ticketDTOContainer.getArrivalTime(),
                ticketDTOContainer.getTrainDTO().getId(), ticketDTOContainer.getTripId())){

            ticketService.setPassengerToTicket(passengerDTO);
            ticketService.createTicket(ticketDTOContainer);
            modelAndView.setViewName("redirect:/alltickets");

        } else {
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

    @GetMapping(value = "/alltickets")
    public ModelAndView showAllTickets() {
        ModelAndView modelAndView = new ModelAndView();
        ticketService.setValidityOfTickets();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<TicketInfoDTO> ticketInfos = ticketService.getAllTicketInfosByUsername(auth.getName());
        modelAndView.addObject("ticketInfos", ticketInfos);
        modelAndView.setViewName("UserTicketsPage");
        return modelAndView;
    }

    @GetMapping(value = "/export/{id}")
    public void exportToPDF(@PathVariable("id")int ticketId, HttpServletResponse response) {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=eTicket_SBB.pdf";
        response.setHeader(headerKey,headerValue);
        ticketService.setValidityOfTickets();
        TicketDTO ticketDTO = ticketService.getTicketDTOById(ticketId);
        TicketPDFExporter pdfExporter = new TicketPDFExporter(ticketDTO);
        pdfExporter.export(response);
    }

}

