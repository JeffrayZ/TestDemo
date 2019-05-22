package com.dialog.sc;

import android.support.v7.app.AlertDialog;

/**
 * @ProjectName: TestDemo
 * @Package: com.dialog.sc
 * @ClassName: IDialog
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2019/5/22 9:45
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/22 9:45
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class IDialog {
    protected AlertDialog dialog = null;
    public abstract void show();
    public abstract void dismiss();
}
