package com.dershi.dao;

import com.dershi.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    // 查询单个用户
    User getUserById(@Param("id") String id);
    User getUserByName(@Param("userName") String username);
    User getUserByNameAndPwd(@Param("userName") String username,@Param("password") String password);
    // 查询所有用户
    List<User> getAllUsers();
    // 添加用户
    int addUser(User user);
    // 删除用户
    int deleteUserById(@Param("id") String id);
    // 更新用户信息
    int updateUserById(Map<String, Object> map);
}
