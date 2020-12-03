package com.carsonlius.springcloudcluster9300;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudCluster9300Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudCluster9300Application.class, args);
    }

}
