package com.nubia.audiofactorytest.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.dolby.DsClient;
import android.dolby.IDsClientEvents;
import android.media.AudioManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemProperties;

/**
 * Created by ZHONGYUAN on 2017/11/6.
 */

public class DisableDolbyService extends Service implements IDsClientEvents {
    private static final String TAG = "dolby";
    private String mAudioEffect;
    private boolean canTest = false,isDsOn = false;
    private static final String PROP_DS_STATE = "dolby.ds.state";
    private DsClient dsClient;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String dsState = SystemProperties.get(PROP_DS_STATE,"");
            isDsOn = dsState.equals("on");
            if (isDsOn&&canTest){
                int result = -1;
                try {
                    result = dsClient.setDsOnChecked(false);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                LogUtils.info(TAG,"result:"+result);
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        try {
            dsClient = new DsClient();
            canTest = true;
            mAudioEffect = SystemProperties.get("audio.effect.name","");
            if (mAudioEffect.equals("dolby")){
                dsClient.setEventListener(this);
                dsClient.bindDsService(this);
                handler.sendEmptyMessageAtTime(100,500);
            }
        } catch (Exception e) {
            LogUtils.info(TAG,e.getMessage());
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onClientConnected() {
        String dsState = SystemProperties.get(PROP_DS_STATE,"");
        isDsOn = dsState.equals("on");
         if (isDsOn&&canTest){
             int result = -1;
             try {
                 result = dsClient.setDsOnChecked(false);
             } catch (RemoteException e) {
                 e.printStackTrace();
             }
             LogUtils.info(TAG,"result:"+result);
         }
    }

    @Override
    public void onClientDisconnected() {

    }

    @Override
    public void onDsOn(boolean b) {

    }

    @Override
    public void onProfileSelected(int i) {

    }

    @Override
    public void onProfileSettingsChanged(int i) {

    }

    @Override
    public void onProfileNameChanged(int i, String s) {

    }

    @Override
    public void onEqSettingsChanged(int i, int i1) {

    }

}
