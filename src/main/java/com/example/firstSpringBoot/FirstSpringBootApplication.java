package com.example.firstSpringBoot;

import com.example.firstSpringBoot.model.RuntimePython;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstSpringBootApplication {

	public static void main(String[] args) {
		Thread thread=new Thread(new RuntimePython());
		thread.start();
		//new RuntimePython().run();
		SpringApplication.run(FirstSpringBootApplication.class, args);
	}

}
