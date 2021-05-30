package com.itgarden;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/external")
public class ShoppingController {

    @GetMapping("/shopping") // http://localhost:8082/api/external/shopping
    public ResponseEntity<String> get() {
        return new ResponseEntity<String>("Shopping External Service", HttpStatus.OK);
    }
}
