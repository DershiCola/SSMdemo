package com.dershi.dao;

import com.dershi.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestUserMapper {
//    @Autowired
//    private UserMapper mapper;

    @Test
    public void testAddUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper mapper = context.getBean(UserMapper.class);
        User user = new User("test", "666666", "test@aa.com");
        mapper.addUser(user);
    }

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper mapper = context.getBean(UserMapper.class);
        List<User> allUsers = mapper.getAllUsers();
        for (User user : allUsers) {
            System.out.println(user);
        }
    }
}
