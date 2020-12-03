package com.carsonlius.springcloudusercoreprovider2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudUserCoreProvider2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudUserCoreProvider2Application.class, args);
    }

}
