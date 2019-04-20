package ideo.com.ideo.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.PowerManager;
import android.widget.Toast;

import ideo.com.ideo.constant.Constant;

public class Util {


    public static void toast(Context context , String text){
        Toast.makeText( context , text, Toast.LENGTH_SHORT).show();
    }

    public static void powermanager( Context context ){
        PowerManager powerManager = (PowerManager) context.getSystemService(context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                Constant.WAKELOCK_NAME);
        wakeLock.acquire();
    }

    public static boolean isMyServiceRunning(Context context , Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
