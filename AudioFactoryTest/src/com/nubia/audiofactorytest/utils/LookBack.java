package com.nubia.audiofactorytest.utils;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;

/**
 * Created by ZHONGYUAN on 2017/11/7.
 */

public class LookBack {
    private static final String TAG = "LookBack";
    private AudioRecord mAudioRecord;
    private AudioTrack mAudioTrack;
    private boolean mLoop = true;
    private int recordBuffer;
    private int trackBuffer;

    public LookBack(){
        recordBuffer = AudioRecord.getMinBufferSize(48000, AudioFormat.CHANNEL_IN_MONO,AudioFormat.ENCODING_PCM_16BIT);
        trackBuffer = AudioTrack.getMinBufferSize(48000, AudioFormat.CHANNEL_OUT_MONO,AudioFormat.ENCODING_PCM_16BIT);
        mAudioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,48000,AudioFormat.CHANNEL_IN_MONO,AudioFormat.ENCODING_PCM_16BIT,recordBuffer*10);
        mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,48000,AudioFormat.CHANNEL_OUT_MONO,AudioFormat.ENCODING_PCM_16BIT,trackBuffer,AudioTrack.MODE_STREAM);
    }

    public void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLoop = true;
                byte[] buffer = new byte[recordBuffer];
                int size;
                mAudioRecord.startRecording();
                mAudioTrack.play();

                while (mLoop&&(size=mAudioRecord.read(buffer,0,recordBuffer))!=-1){
                    mAudioTrack.write(buffer,0,size);
                }
            }
        }).start();
    }

    public void stop(){
        mLoop = false;
        if(null!=mAudioRecord){
            mAudioRecord.stop();
            mAudioRecord.release();
            mAudioRecord = null;
        }
        if (null!=mAudioTrack){
            mAudioTrack.stop();
            mAudioTrack.release();
            mAudioTrack = null;
        }
    }
}
