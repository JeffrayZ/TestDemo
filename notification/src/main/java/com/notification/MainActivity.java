package com.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    NotificationManager manager;
    private int i = 0;
    public static final int REQUEST_CODE = 15;
    int notification_id = 0;
    NotificationCompat.Builder mBuilder;
    //通知管理器
    NotificationManagerCompat managerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 普通通知
                 * */
//                Intent resultIntent = new Intent(MainActivity.this, Main2Activity.class);
//                // ############
//                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
//                stackBuilder.addParentStack(Main2Activity.class);
//                stackBuilder.addNextIntent(resultIntent);
//                // 这三行代码是为了设置点击Main2Activity的返回键回到主界面
//                // ##########
//                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(REQUEST_CODE,PendingIntent.FLAG_UPDATE_CURRENT);
////                long[] vt = {300,100,300,100};
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
//                builder.setSmallIcon(R.mipmap.icon_rating_select)
//                        .setContentTitle("标题")
//                        .setContentText("你好世界")
//                        .setContentIntent(resultPendingIntent)
//                        .setNumber(3) // 设置信息条数
////                        .setContentInfo("3") // 如果是数字，作用同上，设置信息的条数；如果是其他文字，那就是其他文字
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
//                        .setDefaults(NotificationCompat.DEFAULT_ALL) // 设置默认
////                .setVibrate(vt) // 设置震动
////                        .setColor(Color.RED)
//                .setOngoing(false) // true使notification变为ongoing，用户不能手动清除，类似QQ,false或者不设置则为普通的通知
//                .setAutoCancel(true); // 点击消失
//                manager.notify(i++,builder.build());

                /**
                 * 展开通知
                 * */
                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                String[] events = {"1","2","3","4","5","6"};
                // Sets a title for the Inbox in expanded layout
                inboxStyle.setBigContentTitle("Event tracker details:");
                inboxStyle.setSummaryText("mtwain@android.com");
                // Moves events into the expanded layout
                for (int i=0; i < events.length; i++) {
                    inboxStyle.addLine(events[i]);
                }


                Intent resultIntent = new Intent(MainActivity.this, MainActivity.class);
//                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                // ############
//                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
//                stackBuilder.addParentStack(MainActivity.class);
//                stackBuilder.addNextIntent(resultIntent);
//                // 这三行代码是为了设置点击Main2Activity的返回键回到主界面
//                // ##########
//                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(REQUEST_CODE,PendingIntent.FLAG_UPDATE_CURRENT);

                PendingIntent resultPendingIntent = PendingIntent.getActivity(MainActivity.this,REQUEST_CODE,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                builder.setSmallIcon(R.mipmap.icon_rating_select)
                        .setContentTitle("Event tracker")
                        .setContentText("Events received")
                        .setContentIntent(resultPendingIntent)
                        .setStyle(inboxStyle)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setDefaults(NotificationCompat.DEFAULT_ALL) // 设置默认
                        .setOngoing(false)
                        .setAutoCancel(true);
                manager.notify(i++,builder.build());
            }
        });


        findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.btn_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                managerCompat = NotificationManagerCompat.from(MainActivity.this);
                //创建通知
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    NotificationChannel channel = new NotificationChannel(
                            getString(R.string.update_channel_id),
                            getString(R.string.update_channel_name),
                            NotificationManager.IMPORTANCE_DEFAULT);
                    channel.enableVibration(false);
                    channel.setSound(null,null);
                    channel.setVibrationPattern(new long[]{0});
                    mNotificationManager.createNotificationChannel(channel);
                    mBuilder = new NotificationCompat.Builder(MainActivity.this, getString(R.string.update_channel_id));
                } else {
                    mBuilder = new NotificationCompat.Builder(MainActivity.this, null);
                }
                mBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                mBuilder.setColor(Color.parseColor("#ff5000"));
                mBuilder.setContentTitle(getString(R.string.app_name));
                mBuilder.setVibrate(new long[]{0});
                mBuilder.setSound(null);
                mBuilder.setOnlyAlertOnce(true);
                mBuilder.setDefaults(Notification.FLAG_ONLY_ALERT_ONCE);
                mBuilder.setContentText(40 + "%");
                //设置通知不可删除
                mBuilder.setOngoing(true);
                mBuilder.setAutoCancel(false);
                //设置显示通知时间
                mBuilder.setShowWhen(true);
                mBuilder.setProgress(100, 40, false);
                //发送通知
                managerCompat.notify(notification_id, mBuilder.build());


                MyHandler handler = new MyHandler();
                handler.sendEmptyMessage(100);
            }
        });


    }

    int count = 0;

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            count++;
            mBuilder.setContentText(count + "%");
            mBuilder.setProgress(100, count, false);
            //发送通知
            managerCompat.notify(notification_id, mBuilder.build());
            sendEmptyMessageDelayed(100,1000);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
}
