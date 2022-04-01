package com.example.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.CurrencyExchange;
import com.example.demo.exception.InvalidDataProvidedException;
import com.example.demo.repo.CurrencyExchangeRepo;

@RestController
public class CurrencyExchangeController {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	Environment env;

	@Autowired
	CurrencyExchangeRepo repo;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		logger.info("retrieveExchangeValue called with {} to  {}", from,to );
		CurrencyExchange result = repo.findByFromAndTo(from, to);
		if (result == null) {
			throw new InvalidDataProvidedException("No Data Present for " + from + " and " + to + " combination");
		}
		result.setEnvironment(env.getProperty("local.server.port"));
		return result;
	}

}
