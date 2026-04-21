//package com.capgemini.training.middleware.adapter.out.feign;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//public class CorrelationIdFeignInterceptor implements RequestInterceptor {
//
//    @Override
//    public void apply(RequestTemplate template) {
//        template.header("X-Correlation-ID", UUID.randomUUID().toString());
//    }
//}

package com.capgemini.training.middleware.adapter.out.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class CorrelationIdFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String correlationId = MDC.get("correlationId");
        if (correlationId != null) {
            template.header("X-Correlation-Id", correlationId);
        }
    }
}