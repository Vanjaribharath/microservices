//package com.capgemini.training.bff;
//
//import jakarta.annotation.PostConstruct;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class BffGatewayApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(BffGatewayApplication.class, args);
//    }
//
//    @PostConstruct
//    public void testDbLogging() {
//        LoggerFactory.getLogger(getClass())
//                .info("TEST-DB-LOGGING: gateway startup");
//    }
//}
package com.capgemini.training.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class BffGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffGatewayApplication.class, args);
    }
}