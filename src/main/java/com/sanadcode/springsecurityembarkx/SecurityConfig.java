package com.sanadcode.springsecurityembarkx;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated());
        http.formLogin(withDefaults());
        //http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(withDefaults());
        http.csrf( csrf -> {
            csrf.ignoringRequestMatchers("/h2-console/**");
        });
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();
    }

//    public void multiAuth(AuthenticationManagerBuilder auth, @Qualifier("jdbc") UserDetailsService jdbcService, @Qualifier("inMemory") UserDetailsService inMemoryService) throws Exception {
//        auth.userDetailsService(jdbcService).passwordEncoder(passwordEncoder());
//        auth.userDetailsService(inMemoryService);
//    }

//    @Bean
//    @Qualifier("inMemory")
//    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withUsername("msanad").password("{noop}123").roles("ADMIN", "USER").build();
//        UserDetails user = User.withUsername("mtz").password("{noop}123").roles("USER").build();
//        return new InMemoryUserDetailsManager(admin,user);
//    }


    @Bean
    @Qualifier("jdbc")
    UserDetailsService userDetailsService(DataSource dataSource){
//        UserDetails admin = User.withUsername("msanad").password("{noop}123").roles("ADMIN", "USER").build();
//        UserDetails user = User.withUsername("mtz").password("{noop}123").roles("USER").build();
        UserDetails admin = User.withUsername("msanad").password(passwordEncoder().encode("123")).roles("ADMIN", "USER").build();
        UserDetails user = User.withUsername("mtz").password(passwordEncoder().encode("123")).roles("USER").build();
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.createUser(admin);
        jdbcUserDetailsManager.createUser(user);
        return jdbcUserDetailsManager;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
