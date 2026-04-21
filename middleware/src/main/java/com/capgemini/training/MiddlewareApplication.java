//package com.capgemini.training;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//
//@SpringBootApplication
//@EnableFeignClients(
//        basePackages = "com.capgemini.training.middleware.adapter.out.feign"
//)
//public class MiddlewareApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(MiddlewareApplication.class, args);
//    }
//}

package com.capgemini.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.capgemini.training.middleware.adapter.out.feign")
public class MiddlewareApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiddlewareApplication.class, args);
    }
}
