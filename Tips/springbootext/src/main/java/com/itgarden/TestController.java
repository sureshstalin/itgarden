package com.itgarden;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/print") // http://localhost:8080/print
    public String print() {
        return "This is from External Tomcat Server";
    }
}
