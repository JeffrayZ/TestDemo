package com.dialog;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.dialog.sc.IDialog;
import com.dialog.sc.RollDialog;
import com.dialog.sc.RollDialogFactory;

/***
 * http://www.jianshu.com/p/6caffdbcd5db
 *
 *
 *
 * @author Jeffray
 *
 * @created 2017/5/24 16:56
 ***/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 基本用法
         * */
        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog_normal_layout);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setMessage("这是message");
        builder.setTitle("这是title");
        builder.setPositiveButton("确定",null);
        builder.setNegativeButton("取消",null);
        builder.create().show();*/

        /**
         * 通用封装
         * */
        IDialog dialog = new RollDialogFactory().creatDialog(this,null,null);
        dialog.show();
    }
}
