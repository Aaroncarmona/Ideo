package ideo.com.ideo.ui.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ideo.com.ideo.R;

public class ForgottenPassFragment extends Fragment {
    private View view;

    public ForgottenPassFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forgotten_pass , container , false);
        return view;
    }


}
