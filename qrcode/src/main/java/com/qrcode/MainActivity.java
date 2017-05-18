package com.qrcode;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.qrcode.core.QRCodeView;
import com.qrcode.zxing.ZXingView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements QRCodeView.Delegate {
    private QRCodeView mQRCodeView;
    private MediaPlayer mediaPlayer;
    private static final float BEEP_VOLUME = 0.10f;
    private static final long VIBRATE_DURATION = 200L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQRCodeView = (ZXingView) findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);

        initBeepSound();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera(); // 启动相机
        mQRCodeView.showScanRect(); // 显示边框
        mQRCodeView.startSpot(); // 开始识别
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        mediaPlayer.start();
        vibrate();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }

    /**
     * 该方法控制震动
     */
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(VIBRATE_DURATION);
    }

    private void initBeepSound() {
        // The volume on STREAM_SYSTEM is not adjustable, and users found it too loud,
        // so we now play on the music stream.
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.beep);
        try {
            mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
            file.close();
            mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
            mediaPlayer.prepare();
        } catch (IOException e) {
            mediaPlayer = null;
        }
    }
}
