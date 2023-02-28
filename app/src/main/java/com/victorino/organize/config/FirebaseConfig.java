package com.victorino.organize.config;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConfig {
    private static FirebaseAuth auth;
    private static DatabaseReference userConn;

    public static FirebaseAuth getAuth(){
        if (auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

    public static DatabaseReference getUserConn(){
        if (userConn == null){
            userConn = FirebaseDatabase.getInstance().getReference();
        }
        Log.i("firebaseInstance",userConn.toString());
        return userConn;
    }
}
