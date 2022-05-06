package com.example.kkanbu.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("resetCount")
    @Expose
    private int resetCount;

    @SerializedName("role")
    @Expose
    private int role = 1;

    @SerializedName("isRest")
    @Expose
    private boolean isRest = Boolean.FALSE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getResetCount() {
        return resetCount;
    }

    public void setResetCount(int resetCount) {
        this.resetCount = resetCount;
    }

    public boolean isRest() {
        return isRest;
    }

    public void setRest(boolean isRest) {
        this.isRest = isRest;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "Login [id=" + id + ", username=" + username + ", email=" + email + ", mobile=" + mobile
                + ", resetCount=" + resetCount + ", isRest=" + isRest + "]";
    }

    public Login() {
        super();
    }


}
