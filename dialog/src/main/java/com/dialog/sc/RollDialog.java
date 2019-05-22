package com.dialog.sc;

import android.support.v7.app.AlertDialog;

/**
 * @ProjectName: TestDemo
 * @Package: com.dialog.sc
 * @ClassName: RollDialog
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2019/5/22 9:59
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/22 9:59
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RollDialog extends IDialog {
    public RollDialog(AlertDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void show() {
        dialog.show();
    }

    @Override
    public void dismiss() {
        dialog.dismiss();
    }
}
