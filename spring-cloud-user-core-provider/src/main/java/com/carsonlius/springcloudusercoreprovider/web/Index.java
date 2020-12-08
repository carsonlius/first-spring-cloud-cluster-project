package com.carsonlius.springcloudusercoreprovider.web;

import com.carsonlius.springcloudusercoreprovider.Model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Index {
    @GetMapping(value = "/test")
    public Object test()
    {
        return "Eureka Provider Test Function";
    }

    @GetMapping(value = "/testResponseList")
    public List<User> getUsers()
    {
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setAge(28);
        user.setName("carsonlius");

        list.add(user);

        return list;
    }
    @GetMapping(value = "/testParams")
    public User testParams(User user)
    {
        System.out.println("用户 " + user);
        user.setName("奥巴马");
        return user;
    }
}
