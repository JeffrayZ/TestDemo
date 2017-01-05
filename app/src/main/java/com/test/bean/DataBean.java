package com.test.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by modsdom on 2016/6/27.
 */
public class DataBean implements Serializable{
    private String id;
    private String title;
    private List<String> name;

    public DataBean(String id, List<String> name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public DataBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
