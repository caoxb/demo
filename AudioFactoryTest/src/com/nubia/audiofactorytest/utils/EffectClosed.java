package com.nubia.audiofactorytest.utils;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.SystemProperties;

/**
 * Created by ZHONGYUAN on 2017/11/8.
 */
public class EffectClosed {
    private static final String TAG = "EffectClosed";
    private AudioManager mAudioManager;
    private String mAudioEffect;

    private Context mCon;

    public EffectClosed(){
        mAudioEffect = SystemProperties.get("audio.effect.name","");
    }

    public void closeEffect(){
        if (mAudioEffect.equals("dolby")){
            mCon.startService(new Intent(mCon, DisableDolbyService.class));
        }else if (mAudioEffect.equals("srs")){
            mAudioManager.setParameters("srs_cfg:trumedia_enable=0");
        }else {
            LogUtils.info(TAG,"zhe phone no dolby,srs effect");
            return;
        }
    }

    public void setmAudioManager(AudioManager mAudioManager) {
        this.mAudioManager = mAudioManager;
    }

    public void setmCon(Context mCon) {
        this.mCon = mCon;
    }
}

