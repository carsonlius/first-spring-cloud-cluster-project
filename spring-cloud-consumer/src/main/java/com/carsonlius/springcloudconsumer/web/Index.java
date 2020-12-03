package com.carsonlius.springcloudconsumer.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class Index {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/test")
    public Object test()
    {
        String url = "http://USER-CORE-PROVIDER/test";
        ResponseEntity responseEntity= restTemplate.getForEntity(url, String.class);

        Map<String, Object> response = new TreeMap<>();
        response.put("status", 1);
        response.put("body", responseEntity.getBody());
        return response;
    }

}
