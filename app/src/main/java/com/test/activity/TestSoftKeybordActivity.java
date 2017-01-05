package com.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.R;
/********
 *
 * @author： Jeffray zhanfeifei1991@163.com
 *
 * @time： 2016/7/11 10:48
 *
 * @description：
 *
 * 如果你有在 manifest 中为某一个 Activity 设置 android:windowSoftInputMode="adjustResize"，
 * 那么 ScrollView（或其他可以滚动的 ViewGroup）会收缩以显示软键盘。
 * 但如果你在 Activity 的 Theme 中设置了 android:windowFullscreen="true"，ScrollView 就不会这样了，
 * 因为此时 ScrollView 已经被甚至为填充满整个屏幕。此外，在 Theme 中设置 android:fitsSystemWindows="false" 也会使 adjustResize 失效。
 *
 * @Copyright： 2016 www.modosdom.com Inc. All rights reserved.
 ********/
public class TestSoftKeybordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_soft_keybord);
    }
}
