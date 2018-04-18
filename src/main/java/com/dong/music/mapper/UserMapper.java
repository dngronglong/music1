package com.dong.music.mapper;

import com.dong.music.beans.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Component(value = "userMapper")
@Mapper
public interface UserMapper {
    UserBean findUser(UserBean user);
    UserBean findUserByUserName(String userName);
}
