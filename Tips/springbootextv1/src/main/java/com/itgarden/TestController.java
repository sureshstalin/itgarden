package com.itgarden;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/show") // http://localhost:8080/show
    public String show() {
        return "This method from show (External Tomcat)";
    }
}
