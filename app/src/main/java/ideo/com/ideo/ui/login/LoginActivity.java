package ideo.com.ideo.ui.login;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ideo.com.ideo.R;
import ideo.com.ideo.ui.principal.PrincipalActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    public FirebaseAuth mAuth;
    public FirebaseUser currentUser;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponent();
    }

    public void initComponent(){
        this.fragmentManager = getSupportFragmentManager();
        replace(new LoginFragment());
    }

    @Override
    public void onBackPressed() {
        int count = fragmentManager.getBackStackEntryCount();
        Log.d(TAG , count + "");
        if ( count > 0 )
            fragmentManager.popBackStack();
            //finish();
            //super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.currentUser = mAuth.getCurrentUser();
        if ( this.currentUser != null )
            goToPrincipal();
    }

    public void goToPrincipal(){
        startActivity(new Intent( getBaseContext() , PrincipalActivity.class));
    }

    public void replace(Fragment fragment ){
        this.fragmentManager
        .beginTransaction()
        .replace(R.id.fragment , fragment)
        .setCustomAnimations(android.R.anim.fade_in , android.R.anim.fade_out)
        .commit();
    }

    public void add(Fragment fragment ){
        this.fragmentTransaction = this.fragmentManager.beginTransaction();
        this.fragmentTransaction.add(R.id.fragment , fragment)
            .addToBackStack(null)
            .setCustomAnimations(android.R.anim.slide_in_left , android.R.anim.slide_out_right)
            .setTransition(5000)
            .commit();
    }
}
