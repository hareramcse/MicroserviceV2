package com.hs.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("/sample-api")
	// @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
	// @CircuitBreaker(name = "sample-api", fallbackMethod = "hardcodedResponse")
	// @RateLimiter(name="sample-api")
	@Bulkhead(name = "sample-api") // this is for concurrent calls
	public String sampleApi() {
		logger.info("Sample api call received");
		// ResponseEntity<String> forEntity = new
		// RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",
		// String.class);
		return "sample-api";
	}

	public String hardcodedResponse(Exception ex) {
		return "fallback-response";
	}
}
