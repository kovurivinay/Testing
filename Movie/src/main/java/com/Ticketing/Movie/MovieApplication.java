package com.Ticketing.Movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.Ticketing.Movie.Controller.MovieController;

@SpringBootApplication
@ComponentScan(basePackageClasses = MovieController.class)
@ComponentScan("module-service")
public class MovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}

}
