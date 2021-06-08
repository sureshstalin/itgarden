package com.itgarden.controller;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class TestCallable implements Callable<String> {

    @Override
    public String call() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:9094/api/reports/bulk", String.class);
        System.out.println(response);
        return response;
    }
}
