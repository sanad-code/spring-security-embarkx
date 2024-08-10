package com.sanadcode.springsecurityembarkx.youtube;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/csrf")
@Slf4j
public class CsrfController {
    @GetMapping("/token")
    public CsrfToken csrfToken(CsrfToken token) {
        return token;
    }

    @GetMapping("/token1")
    public CsrfToken csrfToken1(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/create")
    public User create(@RequestBody User user){
        var user1 = new User(user.id(), user.name());
        log.info(user1.toString());
        return user1 ;
    }
}
