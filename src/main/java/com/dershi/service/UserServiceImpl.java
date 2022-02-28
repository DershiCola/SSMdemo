package com.dershi.service;

import com.dershi.dao.UserMapper;
import com.dershi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    @Override
    public int addUser(User user) {
        return mapper.addUser(user);
    }

    @Override
    public boolean queryUser(String userName) {
        User user = mapper.getUserByName(userName);
        return user != null;
    }

    @Override
    public boolean queryUser(String userName, String password) {
        User user = mapper.getUserByNameAndPwd(userName, password);
        return user != null;
    }

    @Override
    public List<User> queryAllUsers() {
        return mapper.getAllUsers();
    }

    @Override
    public int deletesUser(String id) {
        return mapper.deleteUserById(id);
    }

    @Override
    public int updatePassword(String uerId, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", uerId);
        map.put("password", password);
        return mapper.updateUserById(map);
    }

    @Override
    public int updateEmail(String userId, String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", email);
        return mapper.updateUserById(map);
    }

}
