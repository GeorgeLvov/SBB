package com.tsystems.javaschool.SBB.config;

import java.util.Properties;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.jms.ConnectionFactory;
import org.springframework.jms.core.JmsTemplate;


import javax.jms.Destination;
import javax.jms.TopicConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;




/*
* standalone.bat -c standalone-full.xml
*/


@Configuration
public class MessagingConfig {

    private final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private final String DESTINATION = "jms/queue/myQueue";

    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {

        final Properties props = new Properties();

        props.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.jboss.naming.remote.client.InitialContextFactory");
        props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8081");
        props.put(Context.SECURITY_PRINCIPAL, "wildfly");
        props.put(Context.SECURITY_CREDENTIALS, "1234");
        Context namingContext = new InitialContext(props);

        ConnectionFactory connectionFactory = (ConnectionFactory) namingContext
                .lookup(CONNECTION_FACTORY);

        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws NamingException {

        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());

        final Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.jboss.naming.remote.client.InitialContextFactory");
        props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8081");
        props.put(Context.SECURITY_PRINCIPAL, "wildfly");
        props.put(Context.SECURITY_CREDENTIALS, "1234");

        Context namingContext = new InitialContext(props);

        Destination destination = (Destination) namingContext
                .lookup(DESTINATION);

        template.setDefaultDestination(destination);

        return template;
    }

}
