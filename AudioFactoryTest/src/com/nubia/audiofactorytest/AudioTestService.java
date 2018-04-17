package com.nubia.audiofactorytest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.nubia.audiofactorytest.manager.AudioTestManager;
import com.nubia.audiofactorytest.manager.DeviceManager;
import com.nubia.audiofactorytest.utils.LogUtils;

/**
 * Created by ZHONGYUAN on 2017/11/11.
 */

public class AudioTestService extends Service {


    private int audio;
    private boolean lock = true;
	@Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	if(null!=intent){
    		audio = intent.getIntExtra("audio",0);
    		LogUtils.info("service", "....onStartCommand...."+",audio:"+audio);  
    		if(audio!=0){
    			selectDeviceAndTest(audio);
    		}else{
    			audioTest();
    		}
    	}
        return super.onStartCommand(intent, flags, startId);
    }

	private void selectDeviceAndTest(int audio) {
		switch(audio){
		case 1:
			if(lock){
				DeviceManager.getInstance().setCurrent_device(DeviceManager.DEVICE_SPEAKER);
				audioTest();
				lock = false;
			}
			break;
		case 2:
			if(lock){
				DeviceManager.getInstance().setCurrent_device(DeviceManager.DEVICE_EARPIECE);
				audioTest();
				lock = false;
			}
			break;
		case 3:
			if(lock){
				DeviceManager.getInstance().setCurrent_device(DeviceManager.DEVICE_MIC1);
				audioTest();
				lock = false;
			}
			break;
		case 4:
			if(lock){
				DeviceManager.getInstance().setCurrent_device(DeviceManager.DEVICE_MIC2);
				audioTest();
				lock = false;
			}
			break;
		case -1:
			AudioTestManager.getInstance(this).stop(DeviceManager.getInstance().getCurrent_device());
			lock = true;
			break;
		}		
	}

	private void audioTest() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				AudioTestManager.getInstance(AudioTestService.this).play(DeviceManager.getInstance().getCurrent_device());
			}
		}).start();
	}

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.info("service", "....onDestroy....");
        if(audio==0){
        	AudioTestManager.getInstance(this).stop(DeviceManager.getInstance().getCurrent_device());
        }
    }
    
}
