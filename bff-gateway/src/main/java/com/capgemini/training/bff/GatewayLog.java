package com.capgemini.training.bff;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "gateway_logs")
@Getter
@Setter
public class GatewayLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String logLevel;

    @Column(columnDefinition = "text")
    private String message;

    @Column(nullable = false)
    private String serviceName;

    @Column(nullable = false)
    private String environment;

    private String traceId;

    private String spanId;

    private String correlationId;

    @Column(nullable = false)
    private Instant eventTime = Instant.now();

    private String httpMethod;

    @Column(columnDefinition = "text")
    private String requestPath;

    private Integer responseStatus;

    private Integer latencyMs;

    @Column(columnDefinition = "text")
    private String loggerName;

    @Column(columnDefinition = "text")
    private String threadName;

    @Column(columnDefinition = "text")
    private String exception;
}
