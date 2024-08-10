package com.sanadcode.springsecurityembarkx.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( authorizeRequests -> authorizeRequests
                // This will allow specific end points and can use **
                .requestMatchers("/contact","/public/**").permitAll()
                // This will deny all end points starting with /admin
                // This can be used in case of maintaining a separate admin module, or deprecated end points
                .requestMatchers("/admin","/admin/**").denyAll()
                .anyRequest().authenticated() );
        // This alone will not show form login and will get popup for basic auth
        http.httpBasic(withDefaults());
        // Data is sent in payload as form data with username, password and _csrf
        // You can check request and response cookie in browser dev tools cookie tab after network tab
//        http.formLogin(withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
