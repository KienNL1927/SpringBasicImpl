package com.example.componentscan;

import com.example.services.SimpleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.services", "com.example.componentscan"})
public class ComponentScanApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ComponentScanApplication.class, args);

		// Get bean from the scanned package
		SimpleService service = context.getBean(SimpleService.class);
		System.out.println(service.greet());
	}
}
