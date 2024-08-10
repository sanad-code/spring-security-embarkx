package com.sanadcode.springsecurityembarkx;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("/hi")
    public String home() {
        return "Welcome to the home page!";
    }
    @RequestMapping("/contact")
    public String contact() {
        return "Contact us at";
    }

    @RequestMapping("/public")
    public String publicPage() {
        return "This is a public page";
    }

    @RequestMapping("/public/hi")
    public String publicPage1() {
        return "This is a public page 1";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "This is an admin page";
    }
}
