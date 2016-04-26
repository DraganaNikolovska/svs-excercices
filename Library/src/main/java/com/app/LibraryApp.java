package com.app;

import java.util.Date;
import java.util.Scanner;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import com.presentation.LibraryController;

@SpringBootApplication
@ComponentScan(basePackages = "com.*")
public class LibraryApp implements CommandLineRunner, ApplicationContextAware {

	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApp.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		context.getBean(LibraryController.class).run();
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.context = arg0;		
	}

}
