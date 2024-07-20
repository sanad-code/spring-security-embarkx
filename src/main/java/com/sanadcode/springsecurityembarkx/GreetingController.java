package com.sanadcode.springsecurityembarkx;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @GetMapping("/hello")
    public String greet() {
        return "Hello, World!";
    }
}
