package com.dong.music.beans;

import javax.persistence.*;

@Entity
@Table(name = "music")
public class SongBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String hash;
    private int u_l_id;

    public SongBean() {
    }

    public int getId() {
        return id;
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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getU_l_id() {
        return u_l_id;
    }

    public void setU_l_id(int u_l_id) {
        this.u_l_id = u_l_id;
    }

    @Override
    public String toString() {
        return "SongBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hash='" + hash + '\'' +
                ", u_l_id=" + u_l_id +
                '}';
    }
}
