package com.tsystems.javaschool.SBB.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class MessageSender {

    @Lazy
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendTextMessage(String message) {
        jmsTemplate.send(session -> session.createObjectMessage(message));
    }

}



