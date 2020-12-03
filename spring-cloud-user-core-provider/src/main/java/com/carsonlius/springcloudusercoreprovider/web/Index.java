package com.carsonlius.springcloudusercoreprovider.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Index {
    @GetMapping(value = "/test")
    public Object test()
    {
        return "Eureka Provider Test Function";
    }
}
