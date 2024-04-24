package com.lingotutor.languageservice;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import feign.Retryer;

@SpringBootApplication
@EnableFeignClients
public class LanguageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanguageServiceApplication.class, args);
	}
	
	
	@Bean
	 Retryer retryer() {
	    return new Retryer.Default(TimeUnit.SECONDS.toMillis(1), TimeUnit.SECONDS.toMillis(5), 10);
	}

}
