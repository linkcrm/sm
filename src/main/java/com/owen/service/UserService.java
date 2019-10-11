package com.owen.service;


import java.util.List;
import java.util.Map;

import com.owen.bean.User;
import com.owen.util.Page;

/**
 * Created by tengj on 2017/4/7.
 */
public interface UserService {
    int add(User learnResouce);
    int update(User learnResouce);
    int deleteById(int ids);
    User queryLearnResouceById(int learnResouce);
    User queryUserByAccount(String fUserName);
    List<User> queryAllUser();
    Page queryLearnResouceList(Map<String,Object> params);
    int updatePw(int userId,String pw);
    List<User> queryAllUsers(String userName);
}
