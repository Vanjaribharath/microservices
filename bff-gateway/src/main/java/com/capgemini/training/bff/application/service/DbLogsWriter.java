//package com.capgemini.training.bff.application.service;
//
//
//import com.capgemini.training.bff.GatewayLog;
//import com.capgemini.training.bff.repo.GatewayLogRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@EnableAsync
//@Service
//public class DbLogsWriter {
//
//    private final GatewayLogRepository repository;
//
//    @Value("${app.service-name}")
//    private String serviceName;
//
//    @Value("${app.environment}")
//    private String environment;
//
//    public DbLogsWriter(GatewayLogRepository repository) {
//        this.repository = repository;
//    }
//
//    /**
//     * ✅ Gateway HTTP logs (success & error)
//     */
//    @Async
//    public void writeHttp(
//            String logLevel,
//            String message,
//            String correlationId,
//            String httpMethod,
//            String requestPath,
//            int responseStatus,
//            long latencyMs,
//            String threadName,
//            String loggerName,
//            String exceptionMessage
//    ) {
//        try {
//            GatewayLog logEntity = new GatewayLog();
//
//            logEntity.setLogLevel(logLevel);
//            logEntity.setMessage(message);
//            logEntity.setCorrelationId(correlationId);
//            logEntity.setHttpMethod(httpMethod);
//            logEntity.setRequestPath(requestPath);
//            logEntity.setResponseStatus(responseStatus);
//            logEntity.setLatencyMs((int) latencyMs);
//            logEntity.setThreadName(threadName);
//            logEntity.setLoggerName(loggerName);
//            logEntity.setException(exceptionMessage);
//
//            logEntity.setServiceName(serviceName);
//            logEntity.setEnvironment(environment);
//
//            repository.save(logEntity);
//
//        } catch (Exception ex) {
//            log.error("Failed to persist gateway log", ex);
//        }
//    }
//
//}