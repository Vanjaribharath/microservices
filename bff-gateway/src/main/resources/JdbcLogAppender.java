package com.capgemini.training.logging;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.MDC;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Custom JDBC Logback Appender for Spring Boot 3
 * Writes structured logs directly into PostgreSQL
 */
public class JdbcLogAppender extends AppenderBase<ILoggingEvent> {

    private DataSource dataSource;

    // This setter is required because logback-spring.xml
    // injects the DataSource bean by name
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void append(ILoggingEvent event) {
        if (dataSource == null) {
            return; // fail silently to avoid recursive logging
        }

        String sql = """
            INSERT INTO application_logs
            (service_name, level, message, logger_name, thread_name, correlation_id)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, MDC.get("service"));
            ps.setString(2, event.getLevel().toString());
            ps.setString(3, event.getFormattedMessage());
            ps.setString(4, event.getLoggerName());
            ps.setString(5, event.getThreadName());
            ps.setString(6, MDC.get("correlationId"));

            ps.executeUpdate();

        } catch (Exception e) {
            // DO NOT log inside appender (causes infinite loop)
            addError("Failed to write log to PostgreSQL", e);
        }
    }
}