package com.example.demo.entity;


	import java.util.Map;

import org.springframework.stereotype.Component;
	@Component
	public interface ExchangeRate {
	    long getTimestamp();
	    String getSource();
	    Map<String, Double> getExchangeRateTable();

	    default Double getRate(String currency) {
	        var key = getSource() + currency;
	        return getExchangeRateTable().get(key);
	    }
	}

