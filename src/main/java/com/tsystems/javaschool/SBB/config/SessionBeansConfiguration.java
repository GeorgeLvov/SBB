package com.tsystems.javaschool.SBB.config;

import com.tsystems.javaschool.SBB.dto.RouteContainer;
import com.tsystems.javaschool.SBB.dto.TicketDTO;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@ComponentScan(basePackages = "com.tsystems.javaschool.SBB")
public class SessionBeansConfiguration {

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RouteContainer getRouteDTOContainer() {
        return new RouteContainer();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public TicketDTO getTicketDTOContainer() {
        return new TicketDTO();
    }

}
