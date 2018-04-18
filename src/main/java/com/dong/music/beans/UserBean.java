package com.dong.music.beans;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //用户登陆名
    private String userName;
    //用户密码
    private String pwd;
    //用户邮箱
    private String email;
    //显示的名字
    private String loginName;
    //列表名字
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "u_id")
    private List<SongListBean> list;
    public UserBean() {
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public List<SongListBean> getList() {
        return list;
    }

    public void setList(List<SongListBean> list) {
        this.list = list;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", loginName='" + loginName + '\'' +
                ", list=" + list +
                '}';
    }
}
