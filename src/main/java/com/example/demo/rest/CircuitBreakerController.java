package com.example.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	//@Retry(name = "sample-api" , fallbackMethod = "hardCodedResponse")
	@CircuitBreaker(name = "default" , fallbackMethod = "hardCodedResponse")
	@RateLimiter(name = "default")
	public String sampleApi() {
		logger.info("Sample API called");
		ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:9999/something", String.class);
		return entity.getBody();
	}
	
	private String hardCodedResponse(Exception ex) {
		logger.info("Looks like Rest call failed --- Calling Fallback response!");

		return "Fallback Response Back";
	}
	
}
