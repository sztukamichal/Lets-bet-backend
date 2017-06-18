package com.dtd.letsbet;

import com.dtd.letsbet.config.LetsBetConfiguration;
import com.dtd.letsbet.controllers.DataProviderController;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class LetsBetApplication {
	public static void main(String[] args) {

		Logger logger = Logger.getLogger(LetsBetApplication.class);

		ApplicationContext ctx = new AnnotationConfigApplicationContext(LetsBetConfiguration.class);

		SpringApplication.run(LetsBetApplication.class, args);
		logger.info("Started!");
	}
}
