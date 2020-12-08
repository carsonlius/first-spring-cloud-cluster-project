package com.carsonlius.springcloudconsumer.web;

import com.carsonlius.springcloudconsumer.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class Index {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/test")
    public Object test()
    {
        String url = "http://USER-CORE-PROVIDER/testResponseList";
        ResponseEntity<List> responseEntity= restTemplate.getForEntity(url, List.class);

        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getBody().getClass().getCanonicalName());
        System.out.println(responseEntity.getBody().getClass().getSimpleName());
        List userList = responseEntity.getBody();
        System.out.println("----------------->");
        for (Object user : userList) {
            System.out.println(user);
            System.out.println(user.getClass().getCanonicalName());
            System.out.println(user.getClass().getName());
        }


        Map<String, Object> response = new TreeMap<>();
        response.put("status", 1);
        response.put("body", responseEntity.getBody());
        return response;
    }


    @GetMapping(value = "/testParams")
    public Object testParams()
    {
        User user = new User();
        user.setAge(33);
        user.setName("川普");
        String url = "http://USER-CORE-PROVIDER/testParams?age={0}&name={1}";
        Object[] params = {33, "川普"};

        ResponseEntity<User> responseEntity =  restTemplate.getForEntity(url, User.class, params);

        User user1 = responseEntity.getBody();
         System.out.println(user1);
        System.out.println(user1.getClass().getCanonicalName());



        return user1;
    }
}
