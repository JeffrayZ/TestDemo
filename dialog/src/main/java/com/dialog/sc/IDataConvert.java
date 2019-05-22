package com.dialog.sc;

/**
 * @ProjectName: TestDemo
 * @Package: com.dialog.sc
 * @ClassName: IDataConvert
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2019/5/22 9:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/22 9:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface IDataConvert<T> {
    CommonResponse<T> convertData(Object jsonObj);
}
