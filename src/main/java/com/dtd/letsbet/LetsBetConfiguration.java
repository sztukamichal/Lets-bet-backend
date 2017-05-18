package com.dtd.letsbet;

import com.dtd.letsbet.model.Footballer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by Michał on 18.05.2017.
 */
@Configuration
public class LetsBetConfiguration {

    @Bean
    public ApplicationContextProvider applicationContextProvider(){
        return new ApplicationContextProvider();
    }

    @Bean({"ronaldo"})
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Footballer footballer(){
        Footballer footballer = new Footballer();
        footballer.setName("Cristiano Ronaldo");
        return footballer;
    }
}
