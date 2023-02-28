package com.victorino.organize.model;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.victorino.organize.config.FirebaseConfig;

public class UserInformation {
    private String id;
    private String name;
    private String password;
    private String email;

    public UserInformation() {
    }
    public void save(){
        DatabaseReference userConf = FirebaseConfig.getUserConn();
        Log.d("gotToData","called to save");
        userConf.child("Users")
                .child(this.id)
                .setValue( this );
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

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
