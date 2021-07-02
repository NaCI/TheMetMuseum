package com.naci.sample.themetmuseum.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        NetworkModule::class,
        DataModule::class
    ]
)
object AppModule {

    @Provides
    fun provideContext(application: Application) = application.applicationContext
}