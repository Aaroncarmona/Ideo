package ideo.com.ideo.ui.principal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ideo.com.ideo.R;
import ideo.com.ideo.ui.login.ForgottenPassFragment;
import ideo.com.ideo.ui.login.LoginFragment;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    protected FirebaseUser currentUser;
    protected FirebaseAuth mAuth;

    protected DrawerLayout drawer;
    protected ActionBarDrawerToggle toggle;

    protected Toolbar toolbar;

    protected FragmentManager fragmentManager;
    protected FragmentTransaction fragmentTransaction;

    protected NavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        initComponents();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initComponents(){
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        nav = findViewById(R.id.nav_view);

        nav.setNavigationItemSelectedListener(this);


        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        checkUser();

        fragmentManager = getSupportFragmentManager();
        replace(new PrincipalFragment());
        getSupportActionBar().setTitle(R.string.principal);
    }

    public void checkUser(){
        if ( currentUser == null ) finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch ( view.getId()){
                //mAuth.signOut();
        }
    }

    public void replace(Fragment fragment ){
        this.fragmentManager
            .beginTransaction()
            .replace(R.id.flPrincipal , fragment)
            .setCustomAnimations(android.R.anim.fade_in , android.R.anim.fade_out)
            .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        boolean fragmentTransaction = false;
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_item_one:
                fragment = new PrincipalFragment();
                fragmentTransaction = true;
                break;
            case R.id.nav_item_two:
                fragment = new ForgottenPassFragment();
                fragmentTransaction = true;
                break;
        }

        if(fragmentTransaction) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.flPrincipal, fragment)
                .commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        drawer.closeDrawers();

        return true;
    }
}