package com.tsystems.javaschool.SBB.config;

import com.tsystems.javaschool.SBB.dto.RouteContainer;
import com.tsystems.javaschool.SBB.utils.TicketPDFExporter;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@ComponentScan(basePackages = "com.tsystems.javaschool.SBB")
public class SessionBeansConfiguration {

    /**
     * Session Bean for creating new route.
     * */
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RouteContainer getRouteContainer() {
        return new RouteContainer();
    }

    /**
     * Session Bean for exporting ticket in PDF format.
     * */
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public TicketPDFExporter getTicketPDFExporter(){
        return new TicketPDFExporter();
    }
}
