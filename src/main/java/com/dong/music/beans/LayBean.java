package com.dong.music.beans;

import java.io.Serializable;
import java.util.List;

public class LayBean implements Serializable {
    private String code;
    private String count;
    private String msg;
    private List<?> data;

    public LayBean() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LayBean{" +
                "code='" + code + '\'' +
                ", count='" + count + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
