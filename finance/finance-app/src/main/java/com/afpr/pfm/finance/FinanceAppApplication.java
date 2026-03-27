package com.afpr.pfm.finance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class FinanceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceAppApplication.class, args);
		log.info("FinanceAppApplication started successfully.");
	}

}
