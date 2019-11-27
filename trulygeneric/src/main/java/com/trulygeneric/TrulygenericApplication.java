package com.trulygeneric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.trulygeneric.batch.service.behaviour.IBatchService;

@SpringBootApplication
public class TrulygenericApplication implements CommandLineRunner {
	
	@Autowired private IBatchService batchService;

	public static void main(String[] args) {
		SpringApplication.run(TrulygenericApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		batchService.start("FINCODE");
	}

}
