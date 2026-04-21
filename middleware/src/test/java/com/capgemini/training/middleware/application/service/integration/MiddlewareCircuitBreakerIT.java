package com.capgemini.training.middleware.application.service.integration;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.serverError;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@WireMockTest(httpPort = 8081)
class MiddlewareCircuitBreakerIT {

    @Autowired
    private TestRestTemplate rest;

    @Test
    void opensCircuitWhenSystemApiKeepsFailing() {

        // Stub system-api to always return 500
        stubFor(
                get(urlMatching("/api/v1/products.*"))
                        .willReturn(serverError())
        );

        // Exceed circuit breaker sliding window (10)
        for (int i = 0; i < 12; i++) {
            rest.getForEntity(
                    "/api/v1/catalog/overview",
                    String.class
            );
        }

        // Next call should hit OPEN circuit
        ResponseEntity<ProblemDetail> response =
                rest.getForEntity(
                        "/api/v1/catalog/overview",
                        ProblemDetail.class
                );

        // Assert circuit breaker OPEN → 503
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
    }
}