package de.timeline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "de.timeline")
public class TimelineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimelineApplication.class, args);
	}
}
