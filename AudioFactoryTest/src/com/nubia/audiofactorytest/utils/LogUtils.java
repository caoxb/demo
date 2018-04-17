package com.nubia.audiofactorytest.utils;

import android.util.Log;

/**
 * Created by ZHONGYUAN on 2017/11/5.
 */

public class LogUtils {
    private static boolean debug = true;

    public static void info(String tag,String msg){
        if (debug)
            Log.i("xbcao:"+tag,msg);
    }

}
