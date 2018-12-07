package com.condorlabs.popularmovies

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication

import com.condorlabs.popularmovies.di.DaggerAppComponent

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector

class MoviesApp : Application(), HasActivityInjector {

    @Inject
    var activityDispatchingInjector: DispatchingAndroidInjector<Activity>? = null

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityDispatchingInjector
    }

    override fun onCreate() {
        super.onCreate()
        initializeComponent()
    }

    private fun initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }



}
