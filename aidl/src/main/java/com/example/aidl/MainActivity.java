package com.example.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.unbind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbind(v);
            }
        });
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("client", "onServiceConnected");
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("client", "onServiceDisconnected");
            iMyAidlInterface = null;
        }
    };

    /**
     * 绑定
     * */
    public void bindService(View view){
        Intent intent = new Intent(this, MyService.class);
        intent.setAction("com.example.aidl");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 解绑
     * */
    public void unbind(View view){
        unbindService(connection);
    }

    /**
     * 解绑
     * */
    public void addInvoked(View view){
        if(iMyAidlInterface != null){
            try {
                int result = iMyAidlInterface.add(12, 12);
                Toast.makeText(this, "结果是：：：：" + result, Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "与服务端断开连接", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 解绑
     * */
    public void minInvoked(View view){
        if(iMyAidlInterface != null){
            try {
                int result = iMyAidlInterface.min(58, 12);
                Toast.makeText(this, "结果是：：：：" + result, Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "与服务端断开连接", Toast.LENGTH_SHORT).show();
        }
    }
}
