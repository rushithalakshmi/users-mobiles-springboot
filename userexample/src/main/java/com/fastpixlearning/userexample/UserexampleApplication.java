package com.fastpixlearning.userexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fastpixlearning.userexample.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@SpringBootApplication
public class UserexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserexampleApplication.class, args);
	}



}
