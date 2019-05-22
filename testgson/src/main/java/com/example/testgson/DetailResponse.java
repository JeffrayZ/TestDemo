package com.example.testgson;

/**
 * @ProjectName: TestDemo
 * @Package: com.example.testgson
 * @ClassName: DetailResponse
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2019/5/9 16:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/9 16:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DetailResponse {
    /**
     * tk : 123badf7632d
     * appVersion : 1.0.0.111
     * sysInfo : Win 7
     * deviceInfo : ThinkPad X1
     * userType : TEA
     * plat : T
     * videoState : 1
     * netType : wifi
     */

    private String tk;
    private String appVersion;
    private String sysInfo;
    private String deviceInfo;
    private String userType;
    private String plat;
    private int videoState;
    private String netType;

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getSysInfo() {
        return sysInfo;
    }

    public void setSysInfo(String sysInfo) {
        this.sysInfo = sysInfo;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public int getVideoState() {
        return videoState;
    }

    public void setVideoState(int videoState) {
        this.videoState = videoState;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }
}
