package com.test.bean;

/**
 * Created by admin on 2016/11/2.
 */

public class UserBean {
    private String name;
    private String age;

    public UserBean(String age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
