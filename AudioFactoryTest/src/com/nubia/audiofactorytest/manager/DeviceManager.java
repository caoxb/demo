package com.nubia.audiofactorytest.manager;

import android.media.AudioManager;
import android.os.Environment;
import android.os.SystemProperties;

/**
 * Created by ZHONGYUAN on 2017/11/5.
 */

public class DeviceManager {
    public static final int DEVICE_NONE = (0<<0);
    public static final int DEVICE_SPEAKER = (1<<0);
    public static final int DEVICE_EARPIECE = (1<<1);
    public static final int DEVICE_MIC1 = (1<<2);
    public static final int DEVICE_MIC2 = (1<<3);

    private int current_device = DEVICE_NONE;
    private static DeviceManager instance;

    public static final String PATH_SPK = Environment.getExternalStorageDirectory().getPath()+"/spk.wav";
    public static final String PATH_REC = Environment.getExternalStorageDirectory().getPath()+"/rec.wav";
    private AudioManager mAudioManager;

    public static DeviceManager getInstance(){
        if (null==instance){
            instance = new DeviceManager();
        }
        return instance;
    }

    private DeviceManager(){}

    public void disableDevice(int deviceID){
        switch (deviceID){
            case DEVICE_SPEAKER:
                SystemProperties.set("debug.audio.speaker","false");
                if (null!=mAudioManager)
                mAudioManager.setParameters("ft_device=none");
                current_device = DEVICE_NONE;
                break;
            case DEVICE_EARPIECE:
                SystemProperties.set("debug.audio.receiver","false");
                if (null!=mAudioManager)
                mAudioManager.setParameters("ft_device=none");
                current_device = DEVICE_NONE;
                break;
            case DEVICE_MIC1:
                SystemProperties.set("debug.audio.mic1","false");
                if (null!=mAudioManager)
                mAudioManager.setParameters("ft_device=none");
                current_device = DEVICE_NONE;
                break;
            case DEVICE_MIC2:
                SystemProperties.set("debug.audio.mic2","false");
                if (null!=mAudioManager)
                mAudioManager.setParameters("ft_device=none");
                current_device = DEVICE_NONE;
                break;
        }
    }

    public void enableDevice(int deviceID){
        switch (deviceID){
            case DEVICE_SPEAKER:
                SystemProperties.set("debug.audio.speaker","true");
                if (null!=mAudioManager)
                mAudioManager.setParameters("ft_device=speaker");
                current_device = DEVICE_SPEAKER;
                break;
            case DEVICE_EARPIECE:
                SystemProperties.set("debug.audio.receiver","true");
                if (null!=mAudioManager)
                mAudioManager.setParameters("ft_device=receiver");
                current_device = DEVICE_EARPIECE;
                break;
            case DEVICE_MIC1:
                SystemProperties.set("debug.audio.mic1","true");
                if (null!=mAudioManager)
                mAudioManager.setParameters("ft_device=mic1");
                current_device = DEVICE_MIC1;
                break;
            case DEVICE_MIC2:
                SystemProperties.set("debug.audio.mic2","true");
                if (null!=mAudioManager)
                mAudioManager.setParameters("ft_device=mic2");
                current_device = DEVICE_MIC2;
                break;
        }
    }

    public boolean deviceEnableIsOk(int deviceId){
        switch (deviceId){
            case DEVICE_SPEAKER:
                if ("true".equals(SystemProperties.get("debug.audio.speaker"))){
                    return true;
                }
                if (null!=mAudioManager&&"speaker".equals(mAudioManager.getParameters("ft_device"))){
                    return true;
                }
                break;
            case DEVICE_EARPIECE:
                if ("true".equals(SystemProperties.get("debug.audio.receiver"))){
                    return true;
                }
                if (null!=mAudioManager&&"receiver".equals(mAudioManager.getParameters("ft_device"))){
                    return true;
                }
                break;
            case DEVICE_MIC1:
                if ("true".equals(SystemProperties.get("debug.audio.mic1"))){
                    return true;
                }
                if (null!=mAudioManager&&"mic1".equals(mAudioManager.getParameters("ft_device"))){
                    return true;
                }
                break;
            case DEVICE_MIC2:
                if ("true".equals(SystemProperties.get("debug.audio.mic2"))){
                    return true;
                }
                if (null!=mAudioManager&&"mic2".equals(mAudioManager.getParameters("ft_device"))){
                    return true;
                }
                break;
        }
        return false;
    }

    public boolean deviceDisableIsOk(int deviceId){
        switch (deviceId){
            case DEVICE_SPEAKER:
                if ("false".equals(SystemProperties.get("debug.audio.speaker"))){
                    return true;
                }
                if (null!=mAudioManager&&"none".equals(mAudioManager.getParameters("ft_device"))){
                    return true;
                }
                break;
            case DEVICE_EARPIECE:
                if ("false".equals(SystemProperties.get("debug.audio.receiver"))){
                    return true;
                }
                if (null!=mAudioManager&&"none".equals(mAudioManager.getParameters("ft_device"))){
                    return true;
                }
                break;
            case DEVICE_MIC1:
                if ("false".equals(SystemProperties.get("debug.audio.mic1"))){
                    return true;
                }
                if (null!=mAudioManager&&"none".equals(mAudioManager.getParameters("ft_device"))){
                    return true;
                }
                break;
            case DEVICE_MIC2:
                if ("false".equals(SystemProperties.get("debug.audio.mic2"))){
                    return true;
                }
                if (null!=mAudioManager&&"none".equals(mAudioManager.getParameters("ft_device"))){
                    return true;
                }
                break;
        }
        return false;
    }
    public String getSdCardPath(int deviceId){
        switch (deviceId){
            case DEVICE_SPEAKER:
                return PATH_SPK;
            case DEVICE_EARPIECE:
                return PATH_REC;
            default:
                return "";
        }
    }


    public int getCurrent_device() {
        return current_device;
    }

    public void setCurrent_device(int current_device) {
        this.current_device = current_device;
    }

    public void setmAudioManager(AudioManager mAudioManager) {
        this.mAudioManager = mAudioManager;
    }
}
