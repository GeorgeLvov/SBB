package com.tsystems.javaschool.SBB.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class MessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String message) {
        jmsTemplate.send(session -> session.createTextMessage(message));
    }
}



