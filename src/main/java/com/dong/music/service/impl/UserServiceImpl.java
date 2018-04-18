package com.dong.music.service.impl;

import com.dong.music.beans.UserBean;
import com.dong.music.mapper.UserMapper;
import com.dong.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 董荣龙
 * @date 2018-04-18 10:33
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserBean findUser(UserBean user) {
        return userMapper.findUser(user);
    }

    @Override
    public UserBean findUserByUserName(String userName) {
        return userMapper.findUserByUserName(userName);
    }
}
