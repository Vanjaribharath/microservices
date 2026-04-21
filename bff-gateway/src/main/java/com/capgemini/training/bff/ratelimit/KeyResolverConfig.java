package com.capgemini.training.bff.ratelimit;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class KeyResolverConfig {

    @Bean
    public KeyResolver userKeyResolver() {
        return exchange ->
                Mono.justOrEmpty(
                        exchange.getRequest()
                                .getHeaders()
                                .getFirst("X-Forwarded-For")
                ).defaultIfEmpty(
                        exchange.getRequest()
                                .getRemoteAddress()
                                .getHostString()
                );
    }
}