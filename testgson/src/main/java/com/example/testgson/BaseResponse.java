package com.example.testgson;

/**
 * @ProjectName: TestDemo
 * @Package: com.example.testgson
 * @ClassName: BaseResponse
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2019/5/9 16:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/9 16:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseResponse<T> {


    /**
     * sn : 0
     * mn : 1
     * v : 1
     * cmd : enterRoom
     * lid : 1234532
     * uid : 1234
     * ts : 15029389892
     * content : {"tk":"123badf7632d","appVersion":"1.0.0.111","sysInfo":"Win 7","deviceInfo":"ThinkPad X1","userType":"TEA","plat":"T","videoState":1,"netType":"wifi"}
     */

    private long sn;
    private long mn;
    private long v;
    private String cmd;
    private String lid;
    private String uid;
    private String ts;
    private T content;

    public long getSn() {
        return sn;
    }

    public void setSn(long sn) {
        this.sn = sn;
    }

    public long getMn() {
        return mn;
    }

    public void setMn(long mn) {
        this.mn = mn;
    }

    public long getV() {
        return v;
    }

    public void setV(long v) {
        this.v = v;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
