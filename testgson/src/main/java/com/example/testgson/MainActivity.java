package com.example.testgson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String string = "{\n" +
                "\"sn\":0,\n" +
                "\"mn\":1,\n" +
                "\"v\":1,\n" +
                "\"cmd\":\"enterRoom\",\n" +
                "\"lid\":\"1234532\",\n" +
                "\"uid\":\"1234\",\n" +
                "\"ts\":\"15029389892\",\n" +
                "\"content\":{\n" +
                "\"tk\":\"123badf7632d\",\n" +
                "\"appVersion\":\"1.0.0.111\",\n" +
                "\"sysInfo\":\"Win 7\", \n" +
                "\"deviceInfo\":\"ThinkPad X1\",\n" +
                "\"userType\":\"TEA\",\n" +
                "\"plat\":\"T\",\n" +
                "\"videoState\":1,\n" +
                "\"netType\":\"wifi\"\n" +
                "}\n" +
                "}";


    }
}
