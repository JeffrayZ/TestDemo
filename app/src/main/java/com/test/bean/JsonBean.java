package com.test.bean;

import java.io.Serializable;

/**
 * Created by modsdom on 2016/6/27.
 */
public class JsonBean implements Serializable{
    private String propertyId;
    private String propertyNameTitle;
    private String showInTopfilter;
    private String propertyName;

    public JsonBean(String propertyId, String showInTopfilter, String propertyName, String propertyNameTitle) {
        this.propertyId = propertyId;
        this.showInTopfilter = showInTopfilter;
        this.propertyName = propertyName;
        this.propertyNameTitle = propertyNameTitle;
    }

    public JsonBean() {
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyNameTitle() {
        return propertyNameTitle;
    }

    public void setPropertyNameTitle(String propertyNameTitle) {
        this.propertyNameTitle = propertyNameTitle;
    }

    public String getShowInTopfilter() {
        return showInTopfilter;
    }

    public void setShowInTopfilter(String showInTopfilter) {
        this.showInTopfilter = showInTopfilter;
    }
}
