package com.victorino.organize.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.victorino.organize.R;

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

    public void registerUser(View view){
        startActivity(new Intent(this,RegisterActivity.class));
    }

    public void haveCount(View view){
        startActivity(new Intent(this,LoginActivity.class));
    }
}