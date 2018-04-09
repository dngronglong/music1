package com.dong.music.beans;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String user_name;

    private String pwd;

    private String email;
    //列表名字
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "u_id")
    private List<SongListBean> list;
    public UserBean() {
    }

    public UserBean(String userName, String pwd, String email) {
        this.user_name = userName;
        this.pwd = pwd;
        this.email = email;
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
        return user_name;
    }

    public void setUserName(String userName) {
        this.user_name = userName;
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
                ", user_name='" + user_name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", list=" + list +
                '}';
    }
}
