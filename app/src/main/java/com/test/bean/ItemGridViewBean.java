package com.test.bean;

/**
 * Created by modsdom on 2016/6/21.
 */
public class ItemGridViewBean {
    private String content;
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public ItemGridViewBean(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
