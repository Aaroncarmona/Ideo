package ideo.com.ideo.oi.models;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LoginResponse {

    @SerializedName("Login")
    private ArrayList<Login> login = null;

    public ArrayList<Login> getLogin() {
        return login;
    }

    public void setLogin(ArrayList<Login> login) {
        this.login = login;
    }
}
