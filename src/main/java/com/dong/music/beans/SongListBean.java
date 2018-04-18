package com.dong.music.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user_list")
public class SongListBean implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int u_id;
    @OneToMany
    @JoinColumn(name = "u_l_id")
    private List<SongBean>list;
    public SongListBean() {
    }

    public int getId() {
        return id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SongListBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", u_id=" + u_id +
                '}';
    }
}
