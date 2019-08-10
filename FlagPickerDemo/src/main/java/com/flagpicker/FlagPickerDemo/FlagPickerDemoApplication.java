package com.flagpicker.FlagPickerDemo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class FlagPickerDemoApplication extends SpringBootServletInitializer{

	
	@Override
 	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
  		return application.sources(FlagPickerDemoApplication.class);
 	}


	public static void main(String[] args) throws IOException {
		SpringApplication.run(FlagPickerDemoApplication.class, args);
	}


}
