package com.example.msg.mvp.entity;

public class MsgListEntity {
    private String name;
    private String msg;
    private String code;

    public MsgListEntity(String name, String msg, String code) {
        this.name = name;
        this.msg = msg;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
