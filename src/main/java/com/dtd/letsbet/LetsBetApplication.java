package com.dtd.letsbet;

import com.dtd.letsbet.model.Footballer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LetsBetApplication {
	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(LetsBetApplication.class);

		ApplicationContext ctx = new AnnotationConfigApplicationContext(LetsBetConfiguration.class);

		Footballer footballer = ctx.getBean(Footballer.class);
		logger.info(footballer.toString());

		SpringApplication.run(LetsBetApplication.class, args);
	}
}
