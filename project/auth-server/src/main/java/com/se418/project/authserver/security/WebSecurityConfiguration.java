package com.se418.project.authserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.
                formLogin()
                    .loginProcessingUrl("/login")
                    .permitAll()
//                    .successHandler(new SuccessLoginHandler())
//                  .failureHandler()
                .and()
                .authorizeRequests()
                    .antMatchers("/oauth/**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .csrf().disable()
                    .cors();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

}

