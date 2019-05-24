package com.example.zhujie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"skjngkjdsjgkls",30000).show();

        getMMSex(666);
        setColor(TestEnum.GREEN);
    }


    private void getMMSex(@TestDef.Sex int sex) {
        switch (sex) {
            case TestDef.MALE:
                break;
            case TestDef.FEMALE:
                break;
            default:
                break;
        }
    }

    private void setColor(TestEnum color) {
        Toast.makeText(this,color.name(),Toast.LENGTH_LONG).show();
    }
}
