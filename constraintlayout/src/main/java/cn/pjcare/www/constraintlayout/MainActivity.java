package cn.pjcare.www.constraintlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        et = (EditText) findViewById(R.id.editText);

        if(savedInstanceState != null){
            et.setText(savedInstanceState.getString("TAG"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("TAG",et.getText().toString());
    }
}
