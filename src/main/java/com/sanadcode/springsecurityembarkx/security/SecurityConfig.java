package com.sanadcode.springsecurityembarkx.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( authorizeRequests -> authorizeRequests
                // This will allow specific end points and can use **
//                .requestMatchers("/contact","/public/**").permitAll()
                // This will deny all end points starting with /admin
                // This can be used in case of maintaining a separate admin module, or deprecated end points
//                .requestMatchers("/admin","/admin/**").denyAll()
                .anyRequest().authenticated() );
        // This alone will not show form login and will get popup for basic auth
        http.httpBasic(withDefaults());
        // This will disable the csrf token
        http.csrf(csrf -> csrf.disable());
        // Data is sent in payload as form data with username, password and _csrf
        // You can check request and response cookie in browser dev tools cookie tab after network tab
//        http.formLogin(withDefaults());
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        UserDetails mai = User.withUsername("mai").password("{noop}mai").roles("mai").build();
        UserDetails malak = User.withUsername("malak").password("{noop}malak").roles("malak").build();
        UserDetails yasso = User.withUsername("yasso").password("{noop}yasso").roles("yasso").build();
        inMemoryUserDetailsManager.createUser(mai);
        inMemoryUserDetailsManager.createUser(malak);
        inMemoryUserDetailsManager.createUser(yasso);
        return inMemoryUserDetailsManager;
        /**
         * we could also create list of UserDetails and pass it to inMemoryManager directly
         */
//     List<UserDetails> users = List.of(mai,malak,yasso);
//     return new InMemoryUserDetailsManager(users);
//     return new InMemoryUserDetailsManager(malak,mai,yasso);
    }
}
