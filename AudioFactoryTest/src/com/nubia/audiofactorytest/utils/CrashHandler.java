package com.nubia.audiofactorytest.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZHONGYUAN on 2017/11/5.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context mCon;
    private static final String PATH = Environment.getExternalStorageDirectory().getPath()+"/Crash/log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".txt";

    public CrashHandler(Context context){
        mCon = context.getApplicationContext();
    }

    public void init(){
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try{
            downExceptionToSDCard(e);
        }catch (IOException ex){
            ex.printStackTrace();
            LogUtils.info(TAG,"down crash info failed!");
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
            LogUtils.info(TAG,"packageManager:not found packageName!");
        }
    }

    private void downExceptionToSDCard(Throwable e) throws IOException, PackageManager.NameNotFoundException {
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            LogUtils.info(TAG,"sdcard unmounted!");
            return;
        }

        File dir = new File(PATH);
        if (!dir.exists()){
            dir.mkdirs();
        }

        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH-mm:ss").format(new Date(current));
        File file = new File(PATH+FILE_NAME+time+FILE_NAME_SUFFIX);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        pw.println(time);
        downPhoneInfo(pw);
        pw.println();
        e.printStackTrace();
        pw.close();
    }

    private void downPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        PackageManager pm = mCon.getPackageManager();
        PackageInfo info = pm.getPackageInfo(mCon.getPackageName(),PackageManager.GET_ACTIVITIES);
        pw.print("App Version:");
        pw.print(info.versionName);
        pw.print("-");
        pw.println(info.versionCode);

        pw.print("OS Version:");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);

        pw.print("Vendor:");
        pw.println(Build.MANUFACTURER);

        pw.print("Model:");
        pw.println(Build.MODEL);

        pw.print("CPU ABI:");
        pw.println(Build.CPU_ABI);
    }
}
