package com.nubia.audiofactorytest.manager;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.nubia.audiofactorytest.utils.EffectClosed;
import com.nubia.audiofactorytest.utils.LogUtils;
import com.nubia.audiofactorytest.utils.LookBack;

import java.io.IOException;

/**
 * Created by ZHONGYUAN on 2017/11/8.
 */
public class AudioTestManager {
    private static final String TAG = "AudioTestManager";
    private static AudioTestManager instance;
    private MediaPlayer mMediaPlayer;
    private Context mCon;
    private AudioManager mAudioManager;
    private DeviceManager mDeviceManager;
    private EffectClosed mEffectClosed;
    private LookBack mLookBack;

    private AudioTestManager(Context context){
        mCon = context;
        mDeviceManager = DeviceManager.getInstance();
        mAudioManager = (AudioManager) mCon.getSystemService(Context.AUDIO_SERVICE);
        mDeviceManager.setmAudioManager(mAudioManager);
        mEffectClosed = new EffectClosed();
        mEffectClosed.setmAudioManager(mAudioManager);
        mEffectClosed.setmCon(mCon);       
        mEffectClosed.closeEffect();
        mMediaPlayer = new MediaPlayer();
        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,maxVolume,0);
    }

    public static AudioTestManager getInstance(Context context){
        if (null==instance){
            instance = new AudioTestManager(context);
        }
        return instance;
    }

    public synchronized void play(int deviceId){
        mDeviceManager.enableDevice(deviceId);
        if (!mDeviceManager.deviceEnableIsOk(deviceId)){
            return;
        }
        switch (deviceId){
            case DeviceManager.DEVICE_SPEAKER:
                audioPlayTest(deviceId);
                break;
            case DeviceManager.DEVICE_EARPIECE:
                audioPlayTest(deviceId);
                break;
            case DeviceManager.DEVICE_MIC1:
                micRecordTest(deviceId);
                break;
            case DeviceManager.DEVICE_MIC2:
                micRecordTest(deviceId);
                break;
            case DeviceManager.DEVICE_NONE:
                break;
        }
    }

    public synchronized void stop(int deviceId){
    	LogUtils.info(TAG, "...stop....");
        mDeviceManager.disableDevice(deviceId);
        if (!mDeviceManager.deviceDisableIsOk(deviceId)){
            return;
        }
        switch (deviceId){
            case DeviceManager.DEVICE_SPEAKER:
                audioTestStop(deviceId);
                break;
            case DeviceManager.DEVICE_EARPIECE:
                audioTestStop(deviceId);
                break;
            case DeviceManager.DEVICE_MIC1:
                audioTestStop(deviceId);
                break;
            case DeviceManager.DEVICE_MIC2:
                audioTestStop(deviceId);
                break;
            case DeviceManager.DEVICE_NONE:
                break;
        }
    }

    private void audioPlayTest(int deviceId){
        String audio_data = mDeviceManager.getSdCardPath(deviceId);
        if (audio_data.equals("")){
            return;
        }
        if (deviceId==DeviceManager.DEVICE_SPEAKER&&mDeviceManager.getCurrent_device()!=DeviceManager.DEVICE_SPEAKER){
            return;
        }
        if (deviceId==DeviceManager.DEVICE_EARPIECE&&mDeviceManager.getCurrent_device()!=DeviceManager.DEVICE_EARPIECE){
            return;
        }
        try {
            if (null!=mMediaPlayer){
            	mMediaPlayer.reset();
                mMediaPlayer.setDataSource(audio_data);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
                mMediaPlayer.setLooping(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void audioTestStop(int deviceId){
    	LogUtils.info(TAG, "...audioTestStop....");
        if (deviceId==DeviceManager.DEVICE_SPEAKER&&mDeviceManager.getCurrent_device()==DeviceManager.DEVICE_NONE&&null!=mMediaPlayer){
            mMediaPlayer.stop();
        }else if (deviceId==DeviceManager.DEVICE_EARPIECE&&mDeviceManager.getCurrent_device()==DeviceManager.DEVICE_NONE&&null!=mMediaPlayer){
            mMediaPlayer.stop();
        }else if (deviceId==DeviceManager.DEVICE_MIC1&&mDeviceManager.getCurrent_device()==DeviceManager.DEVICE_NONE&&null!=mLookBack){
            mLookBack.stop();
        }else if (deviceId==DeviceManager.DEVICE_MIC2&&mDeviceManager.getCurrent_device()==DeviceManager.DEVICE_NONE&&null!=mLookBack){
            mLookBack.stop();
        }else {
            return;
        }
    }



    private void micRecordTest(int deviceId){
        switch (deviceId){
            case DeviceManager.DEVICE_MIC1:
                if (mDeviceManager.getCurrent_device()==DeviceManager.DEVICE_MIC1)
                mLookBack = new LookBack();
                mLookBack.start();
                break;
            case DeviceManager.DEVICE_MIC2:
                if (mDeviceManager.getCurrent_device()==DeviceManager.DEVICE_MIC2)
                mLookBack = new LookBack();
                mLookBack.start();
                break;
        }
    }
}
