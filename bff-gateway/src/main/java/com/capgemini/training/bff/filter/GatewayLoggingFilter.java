//package com.capgemini.training.bff.filter;
//
//import com.capgemini.training.bff.application.service.DbLogsWriter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import static com.capgemini.training.bff.constants.GatewayLogConstants.*;
//
//@Component
//@Slf4j
//public class GatewayLoggingFilter implements GlobalFilter, Ordered {
//
//    private final DbLogsWriter dbLogsWriter;
//
//    public GatewayLoggingFilter(DbLogsWriter dbLogsWriter) {
//        this.dbLogsWriter = dbLogsWriter;
//    }
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//        long startTime = System.currentTimeMillis();
//
//        String method = exchange.getRequest().getMethod() != null
//                ? exchange.getRequest().getMethod().name()
//                : null;
//
//        String path = exchange.getRequest().getURI().getPath();
//
//        String correlationId =
//                exchange.getRequest()
//                        .getHeaders()
//                        .getFirst("X-Correlation-ID");
//
//        return chain.filter(exchange)
//                .doFinally(signalType -> {
//
//                    Integer status =
//                            exchange.getResponse().getStatusCode() != null
//                                    ? exchange.getResponse().getStatusCode().value()
//                                    : null;
//
//                    long latency = System.currentTimeMillis() - startTime;
//
//                    if (status != null && status >= 400) {
//
//                        dbLogsWriter.writeHttp(
//                                LEVEL_ERROR,
//                                MSG_REQUEST_FAILED,
//                                correlationId,
//                                method,
//                                path,
//                                status,
//                                latency,
//                                Thread.currentThread().getName(),
//                                GatewayLoggingFilter.class.getName(),
//                                "HTTP " + status
//                        );
//                    }
//                    else {
//
//                        dbLogsWriter.writeHttp(
//                                LEVEL_INFO,
//                                MSG_REQUEST_COMPLETED,
//                                correlationId,
//                                method,
//                                path,
//                                status,
//                                latency,
//                                Thread.currentThread().getName(),
//                                GatewayLoggingFilter.class.getName(),
//                                null
//                        );
//                    }
//                });
//    }
//
//    @Override
//    public int getOrder() {
//        return -1;
//    }
//}
