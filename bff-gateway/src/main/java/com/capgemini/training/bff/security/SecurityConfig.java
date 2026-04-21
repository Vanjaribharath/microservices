//package com.capgemini.training.bff.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//
//        return http
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(exchanges -> exchanges
//                        .pathMatchers("/actuator/**").permitAll()
//                        .pathMatchers(HttpMethod.GET, "/api/v1/**").hasRole("USER")
//                        .pathMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
//                        .pathMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN")
//                        .pathMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN")
//                        .anyExchange().permitAll()
////                        .anyExchange().authenticated()
//                )
//                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt)
//                .build();
//    }
//}

package com.capgemini.training.bff.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/actuator/**").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt -> {}))
                .build();
    }
}
