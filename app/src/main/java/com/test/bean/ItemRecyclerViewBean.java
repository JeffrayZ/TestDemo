package com.test.bean;

import java.util.ArrayList;

/**
 * Created by modsdom on 2016/6/21.
 */
public class ItemRecyclerViewBean {

    private String title;
    private ArrayList<ItemGridViewBean> igvbList;

    public ItemRecyclerViewBean(String title, ArrayList<ItemGridViewBean> igvbList) {
        this.title = title;
        this.igvbList = igvbList;
    }

    public ArrayList<ItemGridViewBean> getIgvbList() {
        return igvbList;
    }

    public void setIgvbList(ArrayList<ItemGridViewBean> igvbList) {
        this.igvbList = igvbList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
