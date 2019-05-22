package com.dialog.sc;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.dialog.R;

import java.util.Map;

/**
 * @ProjectName: TestDemo
 * @Package: com.dialog.sc
 * @ClassName: RollDialogFactory
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2019/5/22 10:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/22 10:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RollDialogFactory implements IDialogFactory {
    @Override
    public IDialog creatDialog(Context mContext, CommonResponse response, Map<String, Object> map) {
        // 步骤一：创建对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(R.layout.dialog_normal_layout);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setMessage("这是message");
        builder.setTitle("这是title");
        builder.setPositiveButton("确定",null);
        builder.setNegativeButton("取消",null);

        // 步骤二：设置监听
        // ......
        return new RollDialog(builder.create());
    }
}
