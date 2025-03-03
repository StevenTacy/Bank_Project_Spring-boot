package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Currency;
import com.example.demo.entity.ExchangeRate;

import java.util.Collection;
import java.util.Map;

public class CurrencyService implements ExchangeRateService{
	 @Autowired
    private final RestTemplate restTemplate; 
    private final String accessKey;

    public CurrencyService(RestTemplate restTemplate, String accessKey) {
        this.restTemplate = restTemplate;
        this.accessKey = accessKey;
    }
   
    public ExchangeRate getLiveExchangeRate(String sourceCurrency, Collection<String> targetCurrencies) {
        var uriVariables = Map.of(
                "source", sourceCurrency,
                "currencies", String.join(",", targetCurrencies),
                "access_key", accessKey
        );

        return (ExchangeRate) restTemplate.getForObject(
                "/live?format=1&source={source}&currencies={currencies}&access_key={a11298f61a6e8d4363e0b17ab0032449}",
                Currency.class,
                uriVariables
        );
    }
}