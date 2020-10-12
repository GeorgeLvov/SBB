package com.tsystems.javaschool.SBB.config;

import com.tsystems.javaschool.SBB.dto.RouteDTOContainer;
import com.tsystems.javaschool.SBB.dto.TicketDTOContainer;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@ComponentScan(basePackages = "com.tsystems.javaschool.SBB")
public class SessionBeansConfiguration {

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RouteDTOContainer getRouteDTOContainer() {
        return new RouteDTOContainer();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public TicketDTOContainer getTicketDTOContainer() {
        return new TicketDTOContainer();
    }
}
