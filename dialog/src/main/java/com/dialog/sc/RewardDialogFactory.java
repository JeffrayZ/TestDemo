package com.dialog.sc;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import java.util.Map;

/**
 * @ProjectName: TestDemo
 * @Package: com.dialog.sc
 * @ClassName: RewardDialogFactory
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2019/5/22 10:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/22 10:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RewardDialogFactory implements IDialogFactory {
    @Override
    public IDialog creatDialog(Context mContext, CommonResponse response, Map<String, Object> map) {

        return new RewardDialog();
    }
}
