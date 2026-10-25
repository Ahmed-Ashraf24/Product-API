package com.example.demo_api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            // Disable CSRF for REST APIs (not needed for stateless JSON APIs)
            .csrf { it.disable() }
            // Define authorization rules
            .authorizeHttpRequests { auth ->
                auth
                    // Allow public access to your greeting/hello endpoints
                    .requestMatchers("/api/**", "/hello").permitAll()
                    // Require authentication for any other endpoint
                    .anyRequest().authenticated()
            }
            // Optional: If you want basic auth instead of the HTML login page for secured endpoints
            .httpBasic { }

        return http.build()
    }
}