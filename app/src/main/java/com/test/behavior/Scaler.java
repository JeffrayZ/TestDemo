package com.test.behavior;

/**
 * Created by modsdom on 2016/7/4.
 */
public interface Scaler {
    public void setShouldRestore(boolean restore);
    public float getCurrentScale();
    public void cancelAnimations();
    public int getInitialParentBottom();
    public boolean isShouldRestore();
    public boolean isRetracting();
    public float getScale();
    public void updateViewScale();
    public void retractScale();
    public void setScale(float scale);
    public void obtainInitialValues();
}
