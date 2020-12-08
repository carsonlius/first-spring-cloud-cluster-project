package com.carsonlius.springcloudusercoreprovider2.web;

import com.carsonlius.springcloudusercoreprovider2.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Index {
    @GetMapping(value = "test")
    public Object test() {
        return "服务2 /test route 功能测试";
    }

    @GetMapping(value = "/testResponseList")
    public List<User> getUsers()
    {
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setAge(29);
        user.setName("carsonlius");

        list.add(user);

        return list;
    }

    @GetMapping(value = "/testParams")
    public User testParams(User user)
    {
        System.out.println("用户2 " + user);

        user.setName("奥巴马" + Math.random());

        return user;
    }
}
