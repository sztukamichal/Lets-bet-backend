package com.dtd.letsbet;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Michał on 18.05.2017.
 */
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext context;
    public static ApplicationContext getContext(){
        return context;
    }

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
