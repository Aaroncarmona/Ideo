package ideo.com.ideo.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ideo.com.ideo.R;
import ideo.com.ideo.ui.principal.PrincipalActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        delay();
    }

    public void delay(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext() , PrincipalActivity.class));
            }
        } , 2500 );

    }
}