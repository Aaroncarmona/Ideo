package ideo.com.ideo.oi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Login {
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("politics")
    @Expose
    private String politics;
    @SerializedName("urlImg")
    @Expose
    private String urlImg;

    public Login(Integer userid, String name, String username, String email, String politics, String urlImg) {
        this.userId = userid;
        this.name = name;
        this.username = username;
        this.email = email;
        this.politics = politics;
        this.urlImg = urlImg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

}
