package ideo.com.ideo.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ideo.com.ideo.R;
import ideo.com.ideo.ui.principal.PrincipalActivity;
import ideo.com.ideo.util.Util;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private static String TAG = LoginFragment.class.getSimpleName();

    private Button btnLogin;
    private TextInputEditText tietUser , tietPass;
    private TextView tvPass , tvUser;

    private View view;

    public LoginFragment() { }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_login, container, false);
        initComponents();
        return view;
    }


    public void initComponents(){
        ((LoginActivity)getActivity()).mAuth = FirebaseAuth.getInstance();

        this.btnLogin = view.findViewById(R.id.btnLogin);
        this.tietUser = view.findViewById(R.id.tietUser);
        this.tietPass = view.findViewById(R.id.tietPass);
        this.tvUser = view.findViewById(R.id.tvUser);
        this.tvPass = view.findViewById(R.id.tvPass);

        this.btnLogin.setOnClickListener(this);
        this.tvUser.setOnClickListener(this);
        this.tvPass.setOnClickListener(this);
    }

    public void listenerBtnLogin(){
        Log.d(TAG , "listener:tv:pass");

        if ( !validateLogin() ) {
            Util.toast(getActivity().getBaseContext() , getString(R.string.campos_vacios));
            return;
        }

        ((LoginActivity)getActivity()).mAuth.signInWithEmailAndPassword(tietUser.getText().toString(), tietPass.getText().toString())
        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = ((LoginActivity)getActivity()).mAuth.getCurrentUser();
                    ((LoginActivity)getActivity()).goToPrincipal();
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Util.toast( getActivity() , getString(R.string.usuario_contraseña_invalida));
                }
            }
        });
    }

    public boolean validateLogin(){
        if ( tietUser.getText().toString().isEmpty()
                || tietPass.getText().toString().isEmpty() )
            return false;
        return true;
    }
    /*public void listenerBtnLogin() {
        Log.d(TAG , "listener:btn:login::create account");
        mAuth.createUserWithEmailAndPassword(tietUser.getText().toString(), tietPass.getText().toString())
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    //updateUI(user);
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Util.toast(getBaseContext() , getString(R.string.usuario_contraseña_invalida));
                    //updateUI(null);
                }
            }
        });
    }
    */
    public void listenerTvUser() {
        //Util.toast(getActivity(), getString(R.string.en_construccion));
        ((LoginActivity)getActivity()).add(new ForgottenPassFragment());
    }
    public void listenerTvPass() {
        //Util.toast(getActivity() , getString(R.string.en_construccion));
        ((LoginActivity)getActivity()).add(new ForgottenPassFragment());
    }

    @Override
    public void onClick(View view) {
        switch( view.getId() ) {
            case R.id.btnLogin:
                listenerBtnLogin();
                break;
            case R.id.tvUser:
                listenerTvUser();
                break;
            case R.id.tvPass:
                listenerTvPass();
                break;
        }
    }
}
