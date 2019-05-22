package com.dialog.sc;

import android.content.Context;

import java.util.Map;

/**
 * @ProjectName: TestDemo
 * @Package: com.dialog.sc
 * @ClassName: IDialogFactory
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2019/5/22 9:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/22 9:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface IDialogFactory {
    IDialog creatDialog(Context mContext, CommonResponse response, Map<String,Object> map);
}
