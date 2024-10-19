package com.practice.mysqlproj.services;

import org.springframework.web.client.RestTemplate;

public class NumberServiceImpl {
    private final String NUMBERS_API_URL = "http://numbersapi.com/";

    public String getNumberFact(int number) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(NUMBERS_API_URL + number + "/trivia", String.class);
    }
}
