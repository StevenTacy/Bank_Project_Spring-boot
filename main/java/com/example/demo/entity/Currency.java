package com.example.demo.entity;

import java.util.Map;


import org.springframework.stereotype.Component;


@Component
public class Currency {
	
	
	private long timestamp;
	private String source;
	private Map<String, Double> quotes;
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Map<String, Double> getQuotes() {
		return quotes;
	}
	public void setQuotes(Map<String, Double> quotes) {
		this.quotes = quotes;
	}
}
