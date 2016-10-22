package com.swpuiot.qq2018;

import java.io.Serializable;

/**
 * Created by 羊荣毅_L on 2016/10/22.
 */
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String password;
    private int age;
    private String text;

    public UserEntity() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", text='" + text + '\'' +
                '}';
    }
}
