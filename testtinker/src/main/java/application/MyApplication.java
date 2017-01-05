package application;

import android.app.Application;

import com.tencent.bugly.Bugly;

import cn.pjcare.www.testtinker.Config;

/**
 * Created by admin on 2016/12/22.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bugly.init(getApplicationContext(), "2247d941fc", Config.DEBUG);
    }
}
