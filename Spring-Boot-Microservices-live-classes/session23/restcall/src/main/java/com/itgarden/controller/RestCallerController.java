package com.itgarden.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class RestCallerController {


    @GetMapping
    public String doCall() throws Exception {
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(100);
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject("http://localhost:9094/api/reports/customers/1", String.class);
            System.out.println(response);
        }
        return "Call  Completed";
    }

    @GetMapping("/ratelimitDemo") // http://localhost:9095/api/ratelimitDemo
    public String rateLimit() throws Exception {
        for (int i = 0; i < 30; i++) {
            Thread.sleep(1000);
            RestTemplate restTemplate1 = new RestTemplate();
            String response1 = restTemplate1.getForObject("http://localhost:9094/api/reports/ratelimiter", String.class);
            System.out.println(response1);

        }
        return "Ratelimit validation";
    }
}
