package com.dershi.service;

import com.dershi.pojo.User;

import java.util.List;

public interface UserService {
    // 添加用户
    int addUser(User user);

    // 判断用户是否存在
    boolean queryUser(String userName);
    // 判断用户密码是否匹配
    boolean queryUser(String userName, String password);
    // 查询所有用户
    List<User> queryAllUsers();

    // 删除用户
    int deletesUser(String id);

    // 更换密码
    int updatePassword(String userId, String password);

    // 更换邮箱
    int updateEmail(String userId, String email);
}
