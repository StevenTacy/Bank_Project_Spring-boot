package com.example.demo.service;

import com.example.demo.entity.ExchangeRate;


import java.util.Collection;

public interface ExchangeRateService {
    ExchangeRate getLiveExchangeRate(String sourceCurrency, Collection<String> targetCurrencies);
}