package com.condorlabs.popularmovies.di

import android.app.Application

import com.condorlabs.popularmovies.MoviesApp
import com.condorlabs.popularmovies.home.MoviesHomeModule
import com.condorlabs.popularmovies.moviedetail.MovieDetailModule

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, ActivityBuilderModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(aaApp: MoviesApp)
}
