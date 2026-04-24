package com.example.conocimientosbasicos_andorid_studio.di;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @Provides
    @Singleton
    public ExecutorService provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
