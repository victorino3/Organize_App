package com.victorino.organize.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.victorino.organize.R;
import com.victorino.organize.config.FirebaseConfig;

public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setButtonBackVisible(false);
        setButtonNextVisible(false);
        setPageScrollDuration(10);
        setFullscreen(true);
        addSlide(new FragmentSlide.Builder()
                .background(R.color.white)
                .backgroundDark(R.color.black)
                .fragment(R.layout.intro_1)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.white)
                .backgroundDark(R.color.black)
                .fragment(R.layout.intro_2)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.white)
                .backgroundDark(R.color.black)
                .fragment(R.layout.intro_3)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.white)
                .backgroundDark(R.color.black)
                .fragment(R.layout.intro_4)
                //.canGoForward(false)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.white)
                .backgroundDark(R.color.black)
                .fragment(R.layout.intro_cadastro)
                .canGoForward(false)
                .build()
        );
    }
    @Override
    protected void onStart() {
        super.onStart();
        callPrincipal();
    }

    public void callPrincipal(){
        FirebaseAuth getUser = FirebaseConfig.getAuth();
        //getUser.signOut();
        if (getUser.getCurrentUser() != null){
            startActivity(new Intent(this,PrincipalActivity2.class));
        }

    }
    public void registerUser(View view){
        startActivity(new Intent(this,RegisterActivity.class));
    }

    public void haveCount(View view){
        startActivity(new Intent(this,LoginActivity.class));
    }
}