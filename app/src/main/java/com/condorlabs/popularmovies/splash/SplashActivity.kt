package com.condorlabs.popularmovies.splash

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

import com.condorlabs.popularmovies.home.MainActivity


class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            finish()
            startActivity(MainActivity.newIntent(this))
        }, SPLASH_DELAY.toLong())
    }

    companion object {

        private const val SPLASH_DELAY = 2000
    }
}
