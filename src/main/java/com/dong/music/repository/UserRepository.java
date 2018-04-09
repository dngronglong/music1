package com.dong.music.repository;

import com.dong.music.beans.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<UserBean,Integer> {
    @Query(value = "from UserBean as u where u.user_name=?1 and u.pwd=?2")
    public UserBean findUser(String userName,String pwd);

}
