package es.upm.dit.isst.radarcovid.radarwebapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserwebappApplication {

	public static final Logger log = LoggerFactory.getLogger(UserwebappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UserwebappApplication.class, args);
	}

}
