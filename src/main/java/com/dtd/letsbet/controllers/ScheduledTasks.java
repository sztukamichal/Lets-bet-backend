package com.dtd.letsbet.controllers;

import com.dtd.letsbet.ApplicationContextProvider;
import com.dtd.letsbet.LetsBetConfiguration;
import com.dtd.letsbet.model.Footballer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Michał on 18.05.2017.
 */
@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        Footballer footballer = (Footballer) ApplicationContextProvider.getContext().getBean("ronaldo");
        log.info(footballer.getName());
    }



}
