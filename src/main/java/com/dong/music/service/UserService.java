package com.dong.music.service;

import com.dong.music.beans.UserBean;
import org.springframework.stereotype.Service;


public interface UserService {
    UserBean findUser(UserBean user);
    UserBean findUserByUserName(String userName);
}
