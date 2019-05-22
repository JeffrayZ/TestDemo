package com.dialog.sc;

/**
 * @ProjectName: TestDemo
 * @Package: com.dialog.sc
 * @ClassName: CommonResponse
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2019/5/22 9:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/22 9:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CommonResponse<T> {
    T obj;
    int code;
    String msg;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
