package com.tsystems.javaschool.SBB.controller.controllers;

import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.dto.TicketInfo;
import com.tsystems.javaschool.SBB.service.interfaces.*;
import com.tsystems.javaschool.SBB.utils.TicketPDFExporter;
import com.tsystems.javaschool.SBB.validator.TicketValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Log4j2
@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketValidator ticketValidator;
    @Autowired
    private TicketPDFExporter pdfExporter;

    @RequestMapping(value = "/checkin", method = { RequestMethod.POST, RequestMethod.GET})
    public ModelAndView testing(@ModelAttribute TicketDTO ticketDTO,
                                BindingResult bindingResult,
                                @RequestParam String timeSearchFrom,
                                @RequestParam String timeSearchTo) {
        ModelAndView modelAndView = new ModelAndView();
        ticketValidator.validate(ticketDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            String redirectUrl = "redirect:/schedule?stationFrom=" + ticketDTO.getStationFromId()
                    + "&stationTo=" + ticketDTO.getStationToId()
                    + "&dateFrom=" + timeSearchFrom +
                    "&dateTo=" + timeSearchTo +
                    "&err=1";
            modelAndView.setViewName(redirectUrl);
            return modelAndView;
        }

        ticketService.createTicket(ticketDTO);
        modelAndView.setViewName("redirect:/alltickets?success");
        return modelAndView;

    }

    @GetMapping(value = "/alltickets")
    public ModelAndView getAllUserTickets() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<TicketInfo> ticketInfos = ticketService.getAllUserTickets(auth.getName());
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
        ticketService.exportToPDF(ticketId);
        pdfExporter.export(response);
    }

}
