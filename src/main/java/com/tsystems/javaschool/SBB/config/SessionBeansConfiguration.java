package com.tsystems.javaschool.SBB.config;

import com.tsystems.javaschool.SBB.dto.RouteDTOContainer;
import com.tsystems.javaschool.SBB.dto.ScheduleDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTO;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

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
    public TicketDTO getTicketDTOContainer() {
        return new TicketDTO();
    }

}
