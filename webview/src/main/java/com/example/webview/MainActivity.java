package com.example.webview;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri uri = Uri.parse("tbopen://m.taobao.com/tbopen/index.html?source=auto&action=ali.open.nav&module=h5&bootImage=0&h5Url=https://h5.m.taobao.com/bcec/dahanghai-jump.html?spm=2014.ugdhh.4019755093.1221-2611&bc_fl_srcgrowth_dhh_4019755093_1221-2611&spm=2014.ugdhh.4019755093.1221-2611&bc_fl_src=growth_dhh_4019755093_1221-2611&materialid=1221 flg=0x10000000");
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(it);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
