package com.sanadcode.springsecurityembarkx;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("/hi")
    public String home() {
        return "Welcome to the home page!";
    }
}
