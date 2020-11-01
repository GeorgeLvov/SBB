package com.tsystems.javaschool.SBB.config;


import java.util.Properties;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;


import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


@Configuration
public class MessagingConfig {

    private final Properties PROPERTIES;

    {
        PROPERTIES = new Properties();
        PROPERTIES.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        PROPERTIES.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8081");
        PROPERTIES.put(Context.SECURITY_PRINCIPAL, "wildfly");
        PROPERTIES.put(Context.SECURITY_CREDENTIALS, "1234");
    }


    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {

        Context namingContext = new InitialContext(PROPERTIES);

        return (ConnectionFactory) namingContext.lookup("jms/RemoteConnectionFactory");
    }

    @Bean
    public JmsTemplate jmsTemplate() throws NamingException {

        Context namingContext = new InitialContext(PROPERTIES);

        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        Destination destination = (Destination) namingContext.lookup( "jms/queue/myQueue");
        template.setDefaultDestination(destination);

        return template;
    }

}

