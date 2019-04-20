package ideo.com.ideo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import ideo.com.ideo.util.Util;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class IntervalService extends Service {
    public static final String TAG = IntervalService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d( TAG , "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Util.powermanager(getBaseContext());
        Log.d(TAG , "onDestoy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG , "onStartCommand");
        Observable.interval( 1 , TimeUnit.SECONDS , Schedulers.io())
            .subscribe(new Observer<Long>() {
                @Override
                public void onSubscribe(Disposable d) {
                    Log.d(TAG , "onSubscribe");
                }

                @Override
                public void onNext(Long aLong) {
                    Log.d(TAG , String.valueOf(aLong));
                }

                @Override
                public void onError(Throwable e) {
                    Log.d(TAG , "onError");
                }

                @Override
                public void onComplete() {
                    Log.d(TAG , "onComplete");
                }
            });
        return START_STICKY;
    }
}
