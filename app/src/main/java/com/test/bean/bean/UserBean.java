package com.test.bean.bean;

/**
 * Created by admin on 2016/11/2.
 */

public class UserBean {
    private String sex;
    private String address;

    public UserBean(String address, String sex) {
        this.address = address;
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
