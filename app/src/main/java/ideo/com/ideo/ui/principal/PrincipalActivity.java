package ideo.com.ideo.ui.principal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ideo.com.ideo.R;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

}
