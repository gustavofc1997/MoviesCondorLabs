package com.condorlabs.popularmovies.splash;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.condorlabs.popularmovies.home.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private final static int SPLASH_DELAY = 2000;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(() -> {
            finish();
            startActivity(MainActivity.newIntent(this));
        }, SPLASH_DELAY);
    }
}
