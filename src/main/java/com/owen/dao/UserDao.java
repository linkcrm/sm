package com.owen.dao;


import java.util.List;
import java.util.Map;

import com.owen.bean.User;
import com.owen.util.Page;

/**
 * Created by tengj on 2017/4/8.
 */
public interface UserDao {
    int add(User learnResouce);
    int update(User learnResouce);
    int deleteById(int id);
    User queryLearnResouceById(int id);
    List<User> queryAllUsers();
    User queryUserByAccount(String fUserName);
    Page queryLearnResouceList(Map<String,Object> params);
    int updatePw(int userId,String pw);
    List<User> queryAllUsers(String userName);
}
