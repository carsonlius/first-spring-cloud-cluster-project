package com.carsonlius.springcloudconsumer.web;

import com.carsonlius.springcloudconsumer.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
    public Object testParams(String name, Integer age)
    {

        String url = "http://USER-CORE-PROVIDER/testParams?age={age}&name={name}";
        Map<String, Object> map = new TreeMap<>();
        map.put("age", age);
        map.put("name", name);

        ResponseEntity<User> responseEntity =  restTemplate.getForEntity(url, User.class, map);

        User user1 = responseEntity.getBody();
         System.out.println(user1);
        System.out.println(user1.getClass().getCanonicalName());

        return user1;
    }

    @GetMapping(value = "/testParams2")
    public Object testParams2(String name, Integer age)
    {

        String url = "http://USER-CORE-PROVIDER/testParams?age={age}&name={name}";
        Map<String, Object> map = new TreeMap<>();
        map.put("age", age);
        map.put("name", name);

        User user1 =  restTemplate.getForObject(url, User.class, map);

        System.out.println(user1);
        System.out.println(user1.getClass().getCanonicalName());

        return user1;
    }

    @PostMapping(value = "test")
    public Object testPostParams()
    {
        String url = "http://USER-CORE-PROVIDER/testParams";

        Map<String, Object> map = new TreeMap<>();

        Random random = new Random(47);
        int randomNumber = random.nextInt(30);
//        map.put("age", randomNumber);
//        map.put("name", "liusen" + randomNumber);

        LinkedMultiValueMap linkedMultiValueMap = new LinkedMultiValueMap();
        linkedMultiValueMap.add("age", randomNumber);
        linkedMultiValueMap.add("name", "carsonlius" + randomNumber);

        System.out.println("参数"+ map);
        return restTemplate.postForObject(url, linkedMultiValueMap, User.class);
    }

    @PostMapping(value = "/test2")
    public Object testPostParams2(Integer age, String name){

        Map<String, Object> response = new TreeMap<>();
        response.put("name", name);
        response.put("age", age);
        return response;
    }
}
