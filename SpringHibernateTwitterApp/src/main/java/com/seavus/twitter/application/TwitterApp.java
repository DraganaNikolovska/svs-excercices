package com.seavus.twitter.application;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import com.seavus.twitter.presentation.TwitterController;

@SpringBootApplication
@ComponentScan(basePackages = "com.*")
public class TwitterApp implements CommandLineRunner, ApplicationContextAware {

	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(TwitterApp.class, args);

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	@Override
	public void run(String... arg0) throws Exception {
		context.getBean(TwitterController.class).run();

	}

}
