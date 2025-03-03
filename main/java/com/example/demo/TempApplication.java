package com.example.demo;

import java.util.HashMap; 

import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;




@SpringBootApplication
public class TempApplication {

	public static void main(String[] args) {
		SpringApplication.run(TempApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(UserRepository userDao){
	    return args -> {

	        HttpService util = new HttpService();
	        Map<String,String> map = new HashMap<String,String>();
	        map.put("ShopNo", "BA0026_001");
	        String json =new ObjectMapper().writeValueAsString(map);

	        String res=util.post("https://sandbox.sinopac.com/QPay.WebAPI/api/Nonce", json);
	        System.out.println(res);

	    };
	}
}
