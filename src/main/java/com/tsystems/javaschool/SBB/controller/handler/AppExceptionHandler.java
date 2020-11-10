package com.tsystems.javaschool.SBB.controller.handler;

import com.tsystems.javaschool.SBB.controller.controllers.SBBController;
import com.tsystems.javaschool.SBB.controller.controllers.TicketController;
import com.tsystems.javaschool.SBB.exception.NoTicketsException;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.format.DateTimeParseException;

@Log4j2
@ControllerAdvice(assignableTypes = {SBBController.class, TicketController.class})
public class AppExceptionHandler {

    @ExceptionHandler(DateTimeParseException.class)
    public RedirectView dateTimeParseException(RedirectAttributes redirAttr){
        RedirectView redirectView = new RedirectView("/",true);
        redirAttr.addFlashAttribute("errorMessage", "You have entered invalid date!");
        log.info("User has entered invalid date on the main page.");
        return redirectView;
    }


    @ExceptionHandler(NoTicketsException.class)
    public ModelAndView noTicketsException(){
        log.info("User failed to buy a ticket");
        return new ModelAndView("redirect:/alltickets?error");
    }



}
