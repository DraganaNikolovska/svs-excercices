package com.app;

import java.util.Date;
import java.util.Scanner;



import com.data_access.config.DataAccessConfig;
import com.presentation.config.ControllerConfig;
import com.services.config.ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import com.presentation.LibraryController;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan
@Import( {DataAccessConfig.class, ServiceConfig.class, ControllerConfig.class})
public class LibraryApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LibraryApp.class);
		LibraryController controller = context.getBean(LibraryController.class);
		controller.run();
	}


}
