package com.example.map.entity;

public class ActEntity {

    String time;
    String name;

    public ActEntity(String time, String name) {
        this.time = time;
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
