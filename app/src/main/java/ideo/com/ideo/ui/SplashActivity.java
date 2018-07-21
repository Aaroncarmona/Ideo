package ideo.com.ideo.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ideo.com.ideo.R;
import ideo.com.ideo.ui.login.LoginActivity;
import ideo.com.ideo.ui.principal.PrincipalActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        delay(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext() , LoginActivity.class));
            }
        });
    }

    public void delay( Runnable run ){
        new Handler().postDelayed( run , 2500 );

    }
}