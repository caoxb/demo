package com.nubia.audiofactorytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nubia.audiofactorytest.manager.DeviceManager;
import com.nubia.audiofactorytest.utils.LogUtils;

public class MainActivity extends Activity {
    private boolean canClick = true;
    private Intent intent;
    private Button spk_btn,rec_btn,mic1_btn,mic2_btn,stop_btn;
    private static final int UPDATE_TEST_BUTTON = 0;
    private static final int UPDATE_STOP_BUTTON = 1;
    private Handler mHandler = new Handler(){
    	public void handleMessage(android.os.Message msg) {
    		switch(msg.what){
    		case UPDATE_TEST_BUTTON:
    			spk_btn.setEnabled(true);
    			rec_btn.setEnabled(true);
    			mic1_btn.setEnabled(true);
    			mic2_btn.setEnabled(true);
    			break;
    		case UPDATE_STOP_BUTTON:
    			stop_btn.setEnabled(true);
    			break;
    		}
    	};
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        spk_btn = (Button) findViewById(R.id.button);
        rec_btn = (Button) findViewById(R.id.button2);
        mic1_btn = (Button) findViewById(R.id.button3);
        mic2_btn = (Button) findViewById(R.id.button4);
        stop_btn = (Button) findViewById(R.id.button5);
    }

    public void spk(View v){
        if (canClick){
            DeviceManager.getInstance().setCurrent_device(DeviceManager.DEVICE_SPEAKER);
            intent = new Intent(this,AudioTestService.class);
            startService(intent);
            v.setEnabled(false);
            mHandler.sendEmptyMessage(UPDATE_STOP_BUTTON);
            canClick = false;
        }else {
            Toast.makeText(this,"service is running ...",Toast.LENGTH_SHORT).show();
        }
    }

    public void rec(View v){
        if (canClick){
            DeviceManager.getInstance().setCurrent_device(DeviceManager.DEVICE_EARPIECE);
            intent = new Intent(this,AudioTestService.class);
            startService(intent);
            v.setEnabled(false);
            mHandler.sendEmptyMessage(UPDATE_STOP_BUTTON);
            canClick = false;
        }else {
            Toast.makeText(this,"service is running ...",Toast.LENGTH_SHORT).show();
        }
    }

    public void mic1(View v){
        if (canClick){
            DeviceManager.getInstance().setCurrent_device(DeviceManager.DEVICE_MIC1);
            intent = new Intent(this,AudioTestService.class);
            startService(intent);
            v.setEnabled(false);
            mHandler.sendEmptyMessage(UPDATE_STOP_BUTTON);
            canClick = false;
        }else {
            Toast.makeText(this,"service is running ...",Toast.LENGTH_SHORT).show();
        }
    }

    public void mic2(View v){
        if (canClick){
            DeviceManager.getInstance().setCurrent_device(DeviceManager.DEVICE_MIC2);
            intent = new Intent(this,AudioTestService.class);
            startService(intent);
            v.setEnabled(false);
            mHandler.sendEmptyMessage(UPDATE_STOP_BUTTON);
            canClick = false;
        }else {
            Toast.makeText(this,"service is running ...",Toast.LENGTH_SHORT).show();
        }
    }

    public void stop(View v){
         if (null!=intent&&!canClick){
             stopService(intent);
             v.setEnabled(false);
             mHandler.sendEmptyMessage(UPDATE_TEST_BUTTON);
             canClick = true;
             intent = null;
             LogUtils.info("main", "...stop....");
         }
    }
}
