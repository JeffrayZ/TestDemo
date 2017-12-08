package com.recyclerview;

/**
 * 分组项
 * <p>
 * 作者：余天然 on 16/7/15 下午6:28
 */
public class SuperBean {
    String name;
    boolean hasArrow;

    public SuperBean(String name, boolean hasArrow) {
        this.name = name;
        this.hasArrow = hasArrow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasArrow() {
        return hasArrow;
    }

    public void setHasArrow(boolean hasArrow) {
        this.hasArrow = hasArrow;
    }
}
