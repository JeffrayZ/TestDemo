package com.test.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.test.R;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TestRxAndroidActivity extends AppCompatActivity implements View.OnClickListener {

    Button startAsyncTaskButton;
    String url = "http://www.baidu.com";
    int progress;
    Observable operationObservable;
    Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rx_android);

        startAsyncTaskButton = (Button) findViewById(R.id.start_btn);
        startAsyncTaskButton.setOnClickListener(this);

//        Subscription subscription = Single.create(new Single.OnSubscribe() {
//            @Override
//            public void call(SingleSubscriber singleSubscriber) {
//                String value = longRunningOperation();
//                singleSubscriber.onSuccess(value);
//            }
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1() {
//                    @Override
//                    public void call(String value) {
//                        // onSuccess
//                        Snackbar.make(startAsyncTaskButton, value, Snackbar.LENGTH_LONG).show();
//                    }
//                }, new Action1() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        // handle onError
//                    }
//                });

        operationObservable = Observable.create(new Observable.OnSubscribe<Subscriber>() {
            @Override
            public void call(Subscriber subscriber) {
                subscriber.onNext(longRunningOperation());
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()) // subscribeOn the I/O thread
                .observeOn(AndroidSchedulers.mainThread()); // observeOn the UI Thread
    }

    public String longRunningOperation() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // error
        }
        return "Complete!";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn:
                // 原生方法
                // url 作用在doInBackground里面
//                new SampleAsyncTask().execute(url);

                // 用RxAndroid
                startAsyncTaskButton.setEnabled(false);
                operationObservable.subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        startAsyncTaskButton.setEnabled(true);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onNext(String value) {
                        Snackbar.make(startAsyncTaskButton, value, Snackbar.LENGTH_LONG).show();
                    }
                });
                break;
        }
    }

    /**
     * 第一个参数    对应的是doInBackground
     * 第二个参数    对应的是onProgressUpdate
     * 第三个参数    对应的是onPostExecute
     */
    private class SampleAsyncTask extends AsyncTask<String, Integer, String> {

        /**
         * 前期准备
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * 后天运行
         */
        @Override
        protected String doInBackground(String... params) {
//            publishProgress(progress);
            return longRunningOperation();
        }

        /**
         * 完事儿
         */
        @Override
        protected void onPostExecute(String result) {
            Snackbar.make(startAsyncTaskButton, result, Snackbar.LENGTH_LONG).show();
            startAsyncTaskButton.setEnabled(false);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // values[0] 拿到的就是 publishProgress(progress); 传过来的参数
            int i = values[0];
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
