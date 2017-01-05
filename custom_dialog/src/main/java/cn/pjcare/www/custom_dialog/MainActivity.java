package cn.pjcare.www.custom_dialog;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setMessage("这是Message");
//                builder.setNegativeButton("neg", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        builder.create().dismiss();
//                    }
//                });
//                builder.setPositiveButton("pos", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        builder.create().dismiss();
//                    }
//                });
                View v = getLayoutInflater().inflate(R.layout.custom_dialog,null);
//                builder.setView(R.layout.custom_dialog);
                builder.setView(v);
                builder.setCancelable(false);
                builder.create().setCanceledOnTouchOutside(false);

                v.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_SHORT).show();
                    }
                });

                v.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }
}
