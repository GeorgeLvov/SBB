package com.tsystems.javaschool.SBB.controller.handler;

import com.tsystems.javaschool.SBB.controller.controllers.ManagementController;
import com.tsystems.javaschool.SBB.controller.controllers.SBBController;
import com.tsystems.javaschool.SBB.controller.controllers.TicketController;
import com.tsystems.javaschool.SBB.exception.NoTicketsException;
import com.tsystems.javaschool.SBB.exception.TripCompletedException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@Log4j2
@ControllerAdvice(assignableTypes = {ManagementController.class, SBBController.class, TicketController.class})
public class AppExceptionHandler {

    @ExceptionHandler(NoTicketsException.class)
    public ModelAndView noTicketsException() {
        log.info("User failed to buy a ticket");
        return new ModelAndView("redirect:/alltickets?error");
    }

    @ExceptionHandler(TripCompletedException.class)
    public ModelAndView tripCompletedException(Exception exception) {
        log.info(exception.getMessage());
        return new ModelAndView("redirect:/admin/allTrips?error=" + exception.getMessage());
    }


    /*@ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception exception) {
        log.error(exception.getMessage());
        return new ModelAndView("500");
    }*/

}
