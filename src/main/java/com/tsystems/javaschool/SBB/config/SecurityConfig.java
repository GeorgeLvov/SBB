package com.tsystems.javaschool.SBB.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import javax.sql.DataSource;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@ComponentScan("com.tsystems.javaschool")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user", "/admin").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .rememberMe()
                .key("rem-me-key")
                .rememberMeParameter("remember") // it is name of checkbox at login page
                .rememberMeCookieName("rememberLoginCookie") // it is name of the cookie
                .tokenValiditySeconds(10) // remember for number of seconds
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}
/*
    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> list = new ArrayList<>();
        list.add(User.builder()
                .username("user")
                .password(passwordEncoder().encode("1234"))
                .roles("USER").build());
        list.add(User.builder()
                .username("admin")
                .password(passwordEncoder().encode("12345"))
                .roles("ADMIN").build());
        return new InMemoryUserDetailsManager(list);
    }*/