package com.Ticketing.Login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.Ticketing.Login.Controller.LoginController;


@SpringBootApplication
@ComponentScan(basePackageClasses = LoginController.class)
@ComponentScan("module-service")
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

}
