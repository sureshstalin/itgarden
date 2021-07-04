package com.itgarden.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhook")
public class WebhookController {

    @PostMapping // http://localhost:8080/api/webhook
    public ResponseEntity<String> print(@RequestBody String requestBody) {
        System.out.println("###### Webhook #####" + requestBody);
        return new ResponseEntity<String >(requestBody, HttpStatus.OK);
    }
}
