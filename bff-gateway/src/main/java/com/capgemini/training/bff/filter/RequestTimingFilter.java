package com.capgemini.training.bff.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class RequestTimingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {

        long start = System.currentTimeMillis();

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    long duration = System.currentTimeMillis() - start;

                    exchange.getResponse()
                            .getHeaders()
                            .add("X-Response-Time", duration + "ms");

                    log.info("BFF → {} {} took {}ms",
                            exchange.getRequest().getMethod(),
                            exchange.getRequest().getPath(),
                            duration);
                }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}