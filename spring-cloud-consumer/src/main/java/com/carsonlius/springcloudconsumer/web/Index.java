package com.carsonlius.springcloudconsumer.web;

import com.carsonlius.springcloudconsumer.hystrix.CustomHystrixCommand;
import com.carsonlius.springcloudconsumer.model.User;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    @GetMapping(value = "testCustomCommand")
    public Object testCustomCommand()
    {
        String url = "http://USER-CORE-PROVIDER/testHystrix";

        // 执行请求
        CustomHystrixCommand customHystrixCommand = new CustomHystrixCommand(
                // 第一个参数是固定的
                com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")),
                restTemplate,
                url
        );

        return customHystrixCommand.execute();
    }

    /**
    * @HystrixCommand 当前函数激活熔断机制,远程服务超时或者异常，则会自动熔断
     * 属性 @fallbackMethod 指定熔断函数
     * @commandProperties 设置熔断属性
     *      @HystrixProperty 指定具体属性, execution.isolation.thread.timeoutInMilliseconds 超时时间,默认1秒
     * ignoreExceptions 这种异常不进行熔断, 直接抛除异常
    * */
    @GetMapping(value = "/testHystrix")
    @HystrixCommand(fallbackMethod = "error", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
    },
            ignoreExceptions = {Exception.class}
    )
    public Object testHystrix(){
        String url = "http://USER-CORE-PROVIDER/testHystrix";
        ResponseEntity responseEntity= restTemplate.getForEntity(url, String.class);

        return responseEntity.getBody();
    }

    /**
     * 服务降级回调函数
     * @Throwable 捕捉到的异常
     * */
    private String error(Throwable throwable)
    {
        System.out.println(throwable);
        System.out.println(throwable.getClass().getCanonicalName());
        return "熔断函数" ;
    }

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
