package ideo.com.ideo.ui.app;

import android.app.Application;
import android.util.Log;

import ideo.com.ideo.service.IntervalService;
import ideo.com.ideo.util.BroadcastUtil;
import ideo.com.ideo.util.Util;

public class App extends Application {
    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG , "onReceive");
        /*
        ComponentName receiver = new ComponentName(getBaseContext(), AlarmReceiver.class);
        PackageManager pm = getBaseContext().getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        */
        Util.powermanager(getBaseContext());

        if ( !Util.isMyServiceRunning(getBaseContext(), IntervalService.class))
            //startService( new Intent(getBaseContext(), IntervalService.class));

        BroadcastUtil.alarmMangerRepeating(getBaseContext());
    }
}
