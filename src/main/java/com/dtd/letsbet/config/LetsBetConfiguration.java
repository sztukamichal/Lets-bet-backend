package com.dtd.letsbet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Michał on 18.05.2017.
 */
@Configuration
public class LetsBetConfiguration {

    @Bean
    public ApplicationContextProvider applicationContextProvider(){
        return new ApplicationContextProvider();
    }

}
