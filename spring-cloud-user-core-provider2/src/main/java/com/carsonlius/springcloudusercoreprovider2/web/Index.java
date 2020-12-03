package com.carsonlius.springcloudusercoreprovider2.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Index {
    @GetMapping(value = "test")
    public Object test() {
        return "服务2 /test route 功能测试";
    }
}
