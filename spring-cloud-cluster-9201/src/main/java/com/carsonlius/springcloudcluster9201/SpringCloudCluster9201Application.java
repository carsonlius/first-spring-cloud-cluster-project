package com.carsonlius.springcloudcluster9201;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudCluster9201Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudCluster9201Application.class, args);
    }

}
