package com.tsystems.javaschool.SBB.service.impl;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class MessageSender {

    @Lazy
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendTextMessage(String message) {
        try {
            jmsTemplate.send(session -> session.createObjectMessage(message));
        } catch (Exception e) {
            log.error("Failed to send a message into topic!");
        }

    }

}



