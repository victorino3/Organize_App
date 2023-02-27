package com.victorino.organize.model;

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
        userConf.child("Users")
                .child(this.id)
                .setValue(this);
    }

    public String getName() {
        return name;
    }
    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
