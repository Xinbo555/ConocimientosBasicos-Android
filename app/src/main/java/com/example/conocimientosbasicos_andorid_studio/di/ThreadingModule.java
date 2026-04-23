package com.example.conocimientosbasicos_andorid_studio.di;

import android.os.Handler;
import android.os.Looper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ThreadingModule {

    @Provides
    @Singleton
    public Handler provideMainHandler() {
        return new Handler(Looper.getMainLooper());
    }
}
