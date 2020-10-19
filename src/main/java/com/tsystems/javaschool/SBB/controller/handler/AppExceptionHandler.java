package com.tsystems.javaschool.SBB.controller.handler;

import com.tsystems.javaschool.SBB.controller.controllers.SBBController;
import com.tsystems.javaschool.SBB.controller.controllers.TicketController;
import com.tsystems.javaschool.SBB.exception.NoTicketsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.format.DateTimeParseException;


@ControllerAdvice(assignableTypes = {SBBController.class, TicketController.class})
public class AppExceptionHandler {


    @ExceptionHandler(DateTimeParseException.class)
    public RedirectView dateTimeParseException(RedirectAttributes redirAttr){
        RedirectView redirectView = new RedirectView("/",true);
        redirAttr.addFlashAttribute("errorMessage", "You have entered invalid date!");
        return redirectView;
    }


    @ExceptionHandler(NoTicketsException.class)
    public RedirectView noTicketsException(RedirectAttributes redirectAttr){
        RedirectView redirectView = new RedirectView("/alltickets",true);
        redirectAttr.addFlashAttribute("errorMessage",
                "Perhaps there are no more seats available or the train is leaving soon.");
        return redirectView;
    }

}