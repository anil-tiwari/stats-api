package com.dunnhumby.stats_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable()
                   .authorizeExchange()
                   .pathMatchers("/ad/**","/login", "/swagger-ui.html", "/webjars/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                   .anyExchange().authenticated()
                   .and()
                   .build();
    }


}
