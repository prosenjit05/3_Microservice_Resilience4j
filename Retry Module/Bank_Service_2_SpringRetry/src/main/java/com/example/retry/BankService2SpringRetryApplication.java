package com.example.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class BankService2SpringRetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankService2SpringRetryApplication.class, args);
	}

}
