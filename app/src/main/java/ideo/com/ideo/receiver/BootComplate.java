package ideo.com.ideo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import ideo.com.ideo.util.BroadcastUtil;
import ideo.com.ideo.util.Util;

public class BootComplate extends BroadcastReceiver {
    private static final String TAG = BootComplate.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG , "onReceive");

        /*
        ComponentName receiver = new ComponentName(context, AlarmReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        */
        Util.powermanager(context);
        BroadcastUtil.alarmMangerRepeating(context);
    }
}
