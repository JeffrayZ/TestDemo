package com.example.getlauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        List<ResolveInfo> list = getAllInstalledApplication(this, intent);

        for (ResolveInfo info:
                list) {
            String name = info.activityInfo.packageName;
        }
    }

    /**
     * 根据提供的Intent获取所有匹配的应用程序列表
     */
    public static List<ResolveInfo> getAllInstalledApplication(Context context, Intent filter) {
        return context.getPackageManager()
                .queryIntentActivities(filter, PackageManager.MATCH_DEFAULT_ONLY);
    }
}
