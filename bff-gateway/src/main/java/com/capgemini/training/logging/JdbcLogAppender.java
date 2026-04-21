//package com.capgemini.training.logging;
//
//import ch.qos.logback.core.AppenderBase;
//import ch.qos.logback.classic.spi.ILoggingEvent;
//import org.slf4j.MDC;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//
//public class JdbcLogAppender extends AppenderBase<ILoggingEvent> {
//
//    private String url;
//    private String username;
//    private String password;
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//    @Override
//    protected void append(ILoggingEvent event) {
//
//        try {
//            Class.forName("org.postgresql.Driver"); // ✅ force JDBC load
//
//            String sql = """
//    INSERT INTO public.application_logs
//    (service_name, log_level, message, logger_name, thread_name, correlation_id)
//    VALUES (?, ?, ?, ?, ?, ?)
//    """;
//            try (Connection conn =
//                         DriverManager.getConnection(url, username, password);
//                 PreparedStatement ps = conn.prepareStatement(sql)) {
//
//                ps.setString(1, "bff-gateway"); // ✅ FIXED
//                ps.setString(2, event.getLevel().toString());
//                ps.setString(3, event.getFormattedMessage());
//                ps.setString(4, event.getLoggerName());
//                ps.setString(5, event.getThreadName());
//                ps.setString(6, MDC.get("correlationId"));
//
//                ps.executeUpdate();
//            }
//
//        } catch (Exception e) {
//            addError("PostgreSQL log insert failed", e);
//        }
//    }
//}



package com.capgemini.training.logging;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.MDC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcLogAppender extends AppenderBase<ILoggingEvent> {

    // injected from logback-spring.xml
    private String url;
    private String username;
    private String password;
    private String serviceName;
    private String tableName;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    protected void append(ILoggingEvent event) {

        String sql = String.format("""
            INSERT INTO %s
            (service_name, log_level, message, logger_name, thread_name, correlation_id)
            VALUES (?, ?, ?, ?, ?, ?)
            """, tableName);

        try (Connection conn =
                     DriverManager.getConnection(url, username, password);
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setString(1, serviceName);
            ps.setString(2, event.getLevel().toString());
            ps.setString(3, event.getFormattedMessage());
            ps.setString(4, event.getLoggerName());
            ps.setString(5, event.getThreadName());
            ps.setString(6, MDC.get("correlationId"));

            ps.executeUpdate();

        } catch (Exception e) {
            // Logback-safe (does not crash the app)
            addError("Failed to write log to database", e);
        }
    }
}